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

import ninja.Context;
import ninja.Result;
import ninja.params.Param;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.UserDao;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import ninja.Results;
import ninja.i18n.Messages;

@Singleton
public class LoginLogoutController extends BaseController {
    
    @Inject
    LoginLogoutController(Messages msg) {
        this.msg = msg;
    }
    
    @Inject
    UserDao userDao;
    
    public Result postLogin(@Param("useremail") String useremail,
                            @Param("password") String password,
                            Context context) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        
        boolean isUserEmailAndPasswordValid = userDao.isUserAndPasswordValid(useremail, password); 
        
        if (isUserEmailAndPasswordValid){
            String isAdmin;
            isAdmin = String.valueOf(userDao.isUserAdmin(useremail));
            context.getSession().put("useremail", useremail);
            context.getSession().put("admin", isAdmin);
            context.getFlashScope().success("login");
             return Results.text().renderRaw(this.getMsg("login.loginSuccessful", context));
        } else {
            // something is wrong with the input or password not found.
            context.getFlashScope().put("username", useremail);
            context.getFlashScope().error("login"); 
            return Results.text().renderRaw(this.getMsg("login.errorLogin", context));
        }  
    }

    public Result getLogout(Context context) {

        // remove any user dependent information
        context.getSession().clear();
        context.getFlashScope().success("logout");

        return Results.text().renderRaw(this.getMsg("login.logoutSuccessful", context));

    }

}
