/**
 * Copyright (C) 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.GroupDao;
import dao.UserDao;
import java.text.ParseException;
import models.Tgroup;
import models.Tuser;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.i18n.Messages;
import ninja.params.Param;

@Singleton
public class ApiSyncController extends BaseController {

    @Inject
    ApiSyncController(Messages msg) {
        this.msg = msg;
    }

    @Inject
    UserDao userDao;
    @Inject
    GroupDao groupDao;

    public Result postUserChangesJson(@Param("useremail") String useremail,
            @Param("lastmodif") String lastmodif,
            Context context) throws ParseException {

        Tuser user = userDao.getChanges(useremail, this.parseDate(lastmodif));
        user.setUserpassword(""); // No password for client

        return Results.json().render(user);
    }

    public Result postGroupChangesJson(@Param("groupcode") String groupcode,
            @Param("lastmodif") String lastmodif,
            Context context) throws ParseException {

        Tgroup group = groupDao.getChanges(groupcode, this.parseDate(lastmodif));

        return Results.json().render(group);
    }

}
