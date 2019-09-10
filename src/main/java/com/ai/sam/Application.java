package com.ai.sam;

import org.apache.microserv.skeleton.facade.BaseApplication;
import org.apache.microserv.skeleton.facade.Constants;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

//验证：http://localhost:8089/rest/api/doc/index.html
//http://localhost:8089/hystrix//输入：http://localhost:8089/hystrix.stream
//http://127.0.0.1:8089/druid/index.html
//最终要填写多个
@ComponentScan(basePackages={Constants.SYSTEM_CONFIG.SYSTEM_PACKAGE,"com.ai.sam","com.ai.sso"})
public class Application extends BaseApplication {
	public static void main(String[] args) throws FileNotFoundException {
		Application application = new Application();
		application.start(Application.class,args);
		//template取值有：none,normal,unicom
//        String tables = "t_sam_staff_info,t_sam_orga_info,t_sam_group_member,t_sam_group_type,t_sam_group_info," +
//				"t_sam_permit,t_sam_post_info,t_sam_role_mutex," +
//				"t_sam_staff_role,t_sam_role,t_sam_menu,t_sam_auth_element,t_sam_tenant_info," +
//				"t_sam_module,t_sam_province_info,t_sam_district_config,t_sam_password,t_sam_platform_rel," +
//				"t_sam_service_system,t_sam_oauth_client";
//        application.generator(true,"unicom",false,tables);//生成代码
	}

}