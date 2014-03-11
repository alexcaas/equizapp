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

import ninja.Router;
import ninja.application.ApplicationRoutes;
import ninja.utils.NinjaProperties;
import controllers.ApiController;
import controllers.LoginLogoutController;

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
        router.POST().route("/api/login").with(LoginLogoutController.class, "postLogin");
        router.GET().route("/api/logout").with(LoginLogoutController.class, "getLogout");
        router.POST().route("/api/registeruser").with(ApiController.class, "postRegisterUser");
        
        
        router.GET().route("/api/{useremail}/user.json").with(ApiController.class, "getUserByEmailJson");
        //router.GET().route("/api/{username}/articles.xml").with(ApiController.class, "getArticlesXml");
        //router.POST().route("/api/{username}/article.json").with(ApiController.class, "postArticleJson");
        //router.POST().route("/api/{username}/article.xml").with(ApiController.class, "postArticleXml");
 
        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
        //router.GET().route("/assets/.*").with(AssetsController.class, "serve");
        
        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
        //router.GET().route("/.*").with(ApiController.class, "index");
    }

}
