/**
 * Copyright (C) 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package conf;

import com.google.inject.Inject;
import controllers.ApiGroupController;
import controllers.ApiItemController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import ninja.utils.NinjaProperties;
import controllers.ApiUserController;
import controllers.ApiUserGroupController;

public class Routes implements ApplicationRoutes {
    
    @Inject
    NinjaProperties ninjaProperties;

    /**
     * Using a (almost) nice DSL we can configure the router.
     * 
     * The second argument NinjaModuleDemoRouter contains all routes of a
     * submodule. By simply injecting it we activate the routes.
     * 
     * @param router
     *            The default router of this application
     */
    @Override
    public void init(Router router) {  
        

        ///////////////////////////////////////////////////////////////////////
        // Api for management of software
        ///////////////////////////////////////////////////////////////////////
        
        /// USER API
        router.POST().route("/api/login").with(ApiUserController.class, "postLoginJson");
        router.GET().route("/api/logout").with(ApiUserController.class, "getLogout");
        router.GET().route("/api/{useremail}/user").with(ApiUserController.class, "getUserByEmailJson");
        router.POST().route("/api/registeruser").with(ApiUserController.class, "postRegisterUserJson");
        router.POST().route("/api/updateuser").with(ApiUserController.class, "postUpdateUserJson");    
        
        
        /// GROUP API
        router.GET().route("/api/{groupcode}/group").with(ApiGroupController.class, "getGroupByHashedGroupCode");
        router.POST().route("/api/newgroup").with(ApiGroupController.class, "postNewGroupJson");
        router.DELETE().route("/api/deletegroup/{codestr}").with(ApiGroupController.class, "deleteGroup");
        
        /// USERGROUP API
        router.POST().route("/api/joingroup").with(ApiUserGroupController.class, "postJoinGroupJson");
        
        /// ITEM API
        router.POST().route("/api/newitem").with(ApiItemController.class, "postNewItemAndAnswersJson");
        router.POST().route("/api/updateitem").with(ApiItemController.class, "postUpdateItemAndAnswersJson");
        router.DELETE().route("/api/deleteitem/{itemid}").with(ApiItemController.class, "deleteItem");
        
        /// SYNC API

 
        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
        //router.GET().route("/assets/.*").with(AssetsController.class, "serve");
        
        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
        //router.GET().route("/.*").with(ApiUserController.class, "index");
    }

}
