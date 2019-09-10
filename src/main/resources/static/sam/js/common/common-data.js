/*
 * 数据转换
 */
define(["crm"], function(CRM) {
	
	/*
	 * easyui-tree数据转换
	 */
	function Node(id, pid, text) {
		
		this.id = id;
		
		this.pid = pid;
		
		this.text = text;
		
		this._p = undefined;
		
	}
	
	CRM.easyui_tree_data = function(obj, rootId) {
		
		var data = obj.data,
			idField = obj.idField,
			pidField = obj.pidField,
			textField = obj.textField;
		
		var nodes = [],
			arr = [],
			pNull = {};
		
		var rootNode = new Node(rootId || -1);

		for (var i = 0, l = data.length; i < l; i++) {
			
			var item = data[i];
			
			nodes.push(new Node(item[idField], item[pidField], item[textField]));
				
		}
		
		var pNode, node, children;
		
		for (var n = 0; n < l; n++) {
			
			pNode = null;
			
			node = nodes[n];
			
			children = pNull[node.id];							//查看该节点是否已经有子节点呗格式化过
			
			if (children) {										//若该节点的子节点先进行了格式化
				
				for (var i = 0, il = children.length; i < il; i++) {
					
					children[i]._p = node;
					
				}
				
				node.children = children;
				
				delete children;
				
			}
			
			if (node.pid == rootNode.id) {
				
				pNode = rootNode;
				
			} else {
				
				/*
				 * 开始找父节点
				 */
				for (var m = n; m--;) {							//从当前位置往前找
					
					if (nodes[m].id == node.pid) {
						
						pNode = nodes[m];
						
						break;
					
					} else if (nodes[m].pid == node.pid) {
						
						pNode = nodes[m]._p;
						
						break;
						
					}
					
				}
				
				if (!pNode) {									//往后找
					
					for (var k = n + 1; k < l; k++) {
						
						if (nodes[k].id == node.pid) {
							
							pNode = nodes[k];
							
							break;
						}
					}
				}
				
			}
			
			/*
			 * 如果找到了父节点
			 */
			if (pNode) {
				
				node._p = pNode;
				
				pNode.children ? pNode.children.push(node) : (pNode.children = [node]);
				
			} else {
				
				if (pNull[node.pid]) {
					
					pNull[node.pid].push(node);
					
				} else {
					
					pNull[node.pid] = [node];
					
				}
				
			}
			
		}
		
		return rootNode.children;
		
	};
	
	return {
		easyui_tree_data: CRM.easyui_tree_data
	};
	
});