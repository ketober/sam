/*
 * 时间格式化
 * 格式化为[yyyy-mm-dd 00:00:00]
 */
define(["easyui"], function() {
	
	function fz(num) {
		
		return +num < 10 ? "0" + num : num;
		
	}
	
	var link = "-";
	
	$.fn.datebox.defaults.formatter = function(date) {
		
		return [date.getFullYear(), fz(+date.getMonth() + 1), fz(date.getDate())].join(link) + " 00:00:00";

	};

	$.fn.datebox.defaults.parser = function(s) {
		
		var t = Date.parse(s);
		
		if (!isNaN(t)) {
			
			return new Date(t);
		
		} else {
		
			return new Date();
		
		}
		
	};
	
});
