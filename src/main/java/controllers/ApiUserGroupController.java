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
import dao.GroupDao;
import dao.UsergroupDao;
import models.Tuser;
import models.Tusergroup;
import ninja.Context;
import ninja.i18n.Messages;
import ninja.params.Param;


@Singleton
public class ApiUserGroupController extends BaseController {
      
    @Inject
    ApiUserGroupController (Messages msg) {
        this.msg = msg;
    }

    @Inject
    UsergroupDao usergroupDao;
    @Inject
    GroupDao groupDao;
    
    
    public Result postJoinGroupJson(@Param("useremail") String useremail,
                            @Param("codestr") String groupcodestr,                            
                            Context context) {

        Tusergroup usergroup = usergroupDao.joinGroup(useremail, groupcodestr);
        
        if (usergroup != null) {
                Tuser user = usergroup.getTuser();
                user.setUserpassword(""); // No password sent to client
                context.getFlashScope().success("postjoingroupok");
                return Results.json().render(user);
            } else {
                context.getFlashScope().error("postjoingroupfail");
                return Results.text().renderRaw(this.getMsg("usergroup.postJoinGroupFail", context));
            }
    }
    
}
