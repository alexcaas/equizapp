/**
 */

package conf;

import com.google.inject.Inject;
import controllers.ApiGroupController;
import controllers.ApiItemController;
import controllers.ApiSyncController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import ninja.utils.NinjaProperties;
import controllers.ApiUserController;
import controllers.ApiUserGroupController;
import controllers.BaseController;
import ninja.AssetsController;

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
        router.GET().route("/api/getuser/{useremail}").with(ApiUserController.class, "getUserByEmailJson");
        router.POST().route("/api/registeruser").with(ApiUserController.class, "postRegisterUserJson");
        router.POST().route("/api/updateuser").with(ApiUserController.class, "postUpdateUserJson");    
        
        
        /// GROUP API
        router.GET().route("/api/getgroup/{groupcode}").with(ApiGroupController.class, "getGroupByGroupCode");
        router.GET().route("/api/gethashedgroup/{grouphashedcode}").with(ApiGroupController.class, "getGroupByHashedGroupCode");
        router.POST().route("/api/newgroup").with(ApiGroupController.class, "postNewGroupJson");
        router.DELETE().route("/api/deletegroup/{codestr}").with(ApiGroupController.class, "deleteGroup");
        
        /// USERGROUP API
        router.POST().route("/api/linkgroup").with(ApiUserGroupController.class, "postLinkGroupJson");
        router.POST().route("/api/unlinkgroup").with(ApiUserGroupController.class, "postUnLinkGroupJson");
        
        /// ITEM API
        router.POST().route("/api/newitem").with(ApiItemController.class, "postNewItemAndAnswersJson");
        router.POST().route("/api/updateitem").with(ApiItemController.class, "postUpdateItemAndAnswersJson");
        router.DELETE().route("/api/deleteitem/{itemid}").with(ApiItemController.class, "deleteItem");
        
        /// SYNC API
//        router.POST().route("/api/userchanges").with(ApiSyncController.class, "postUserChangesJson");
//        router.POST().route("/api/groupchanges").with(ApiSyncController.class, "postGroupChangesJson");
//        router.POST().route("/api/updatetrait").with(ApiSyncController.class, "postUpdateUserTrait");
        router.POST().route("/api/syncuser").with(ApiSyncController.class, "postUserSyncJson");
 
        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////   
        router.GET().route("/assets/{fileName: .*}").with(AssetsController.class, "serveStatic");
        
        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/.*").with(BaseController.class, "index");
    }

}
