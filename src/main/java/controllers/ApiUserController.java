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

package controllers;

import ninja.Result;
import ninja.Results;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.Tuser;
import dao.UserDao;
import dao.UsergroupDao;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import ninja.Context;
import ninja.i18n.Messages;
import ninja.params.Param;
import ninja.params.PathParam;


@Singleton
public class ApiUserController extends BaseController {
      
    @Inject
    ApiUserController (Messages msg) {
        this.msg = msg;
    }

    @Inject
    UserDao userDao;
    @Inject
    UsergroupDao userGroupDao;
    
    
    public Result postLoginJson(@Param("useremail") String useremail,
                            @Param("password") String password,
                            Context context) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        
        boolean isUserEmailAndPasswordValid = userDao.isUserAndPasswordValid(useremail, password); 
        
        if (isUserEmailAndPasswordValid){
            String isAdmin;
            Tuser user = (Tuser) userDao.getUserByEmail(useremail);
            isAdmin = String.valueOf(user.getUseradmin());
            context.getSession().put("useremail", useremail);
            context.getSession().put("admin", isAdmin);
            context.getFlashScope().success("postloginok");
            user.setUserpassword(""); // No password sent to client
            return Results.json().render(user);
        } else {
            context.getFlashScope().put("username", useremail);
            context.getFlashScope().error("postloginfail"); 
            return Results.text().renderRaw(this.getMsg("login.postLoginFail", context));
        }  
    }

    public Result getLogout(Context context) {

        context.getSession().clear();
        context.getFlashScope().success("getlogoutok");

        return Results.text().renderRaw(this.getMsg("login.getLogoutOk", context));

    }

    public Result getUserByEmailJson(@PathParam("useremail") String useremail, Context context) {
        
        Tuser user = (Tuser) userDao.getUserByEmail(useremail);
        
        if (user == null) {
            context.getFlashScope().error("getuserbyemailjsonfail");
            return Results.text().renderRaw(this.getMsg("user.getUserByEmailJsonFail", context));
        } else {
            user.setUserpassword(""); // No password sent to client
            return Results.json().render(user);            
        }     

    }
    
    public Result postRegisterUserJson(@Param("useremail") String useremail, 
            @Param("username") String username,
            @Param("userlastnames") String userlastnames,
            @Param("userpassword") String userpassword,
            @Param("useradmin") String useradmin,
            Context context) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        
        Tuser user = (Tuser) userDao.getUserByEmail(useremail);
        
        if (user != null) {
            context.getFlashScope().error("postregisteruseralreadyexists");
            return Results.text().renderRaw(this.getMsg("user.postRegisterUserAlreadyExists", context));
        } else {
            user = (Tuser) userDao.postRegisterUser(useremail, username, userlastnames, userpassword, Boolean.parseBoolean(useradmin));
            if (user != null) {
                this.postLoginJson(useremail, userpassword, context);
                user.setUserpassword(""); // No password sent to client
                context.getFlashScope().success("postregisteruserok");
                return Results.json().render(user);
            } else {
                context.getFlashScope().error("postregisteruserfail");
                return Results.text().renderRaw(this.getMsg("user.postRegisterUserFail", context));
            }
        }     

    }
    
    public Result postUpdateUserJson(@Param("useremail") String useremail, 
            @Param("username") String username,
            @Param("userlastnames") String userlastnames,
            @Param("userpassword") String userpassword,
            Context context) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
      
        Tuser user = (Tuser) userDao.postUpdateUser(useremail, username, userlastnames, userpassword);
        
        if (user != null) {
            user.setUserpassword(""); // No password sent to client
            context.getFlashScope().error("postupdateuserok");
            return Results.json().render(user);
        } else {
            context.getFlashScope().error("postupdateuserfail");
            return Results.text().renderRaw(this.getMsg("user.postUpdateUserFail", context));
        }     

    }
}
