/**
 */
package controllers;

import ninja.Result;
import ninja.Results;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.GroupDao;
import dao.ItemDao;
import java.util.ArrayList;
import java.util.List;
import models.Tanswer;
import models.Titem;
import ninja.Context;
import ninja.i18n.Messages;
import ninja.params.Param;
import ninja.params.PathParam;

@Singleton
public class ApiItemController extends BaseController {

    @Inject
    ApiItemController(Messages msg) {
        this.msg = msg;
    }

    @Inject
    ItemDao itemDao;
    @Inject
    GroupDao groupDao;

    public Result postNewItemAndAnswersJson(@Param("itemstring") String itemstring,
            @Param("itemdifficulty") String itemdifficulty,
            @Param("groupcode") String groupcode,
            @Param("answerstring1") String answerstring1,
            @Param("answercorrect1") String answercorrect1,
            @Param("answerstring2") String answerstring2,
            @Param("answercorrect2") String answercorrect2,
            @Param("answerstring3") String answerstring3,
            @Param("answercorrect3") String answercorrect3,
            Context context) {

        try {

            Short itemdifficultysi = Short.parseShort(itemdifficulty);
            Boolean answercorrect1bool = Boolean.parseBoolean(answercorrect1);
            Boolean answercorrect2bool = Boolean.parseBoolean(answercorrect2);
            Boolean answercorrect3bool = Boolean.parseBoolean(answercorrect3);

            Titem item = new Titem(itemstring, itemdifficultysi, groupDao.getGroupByHashedGroupCode(groupcode));

            List<Tanswer> answers = new ArrayList();
            Tanswer answer1 = new Tanswer(item, answerstring1, answercorrect1bool);
            Tanswer answer2 = new Tanswer(item, answerstring2, answercorrect2bool);
            Tanswer answer3 = new Tanswer(item, answerstring3, answercorrect3bool);

            answers.add(answer1);
            answers.add(answer2);
            answers.add(answer3);

            item.setTanswerCollection(answers);

            itemDao.postNewItem(item);

            context.getFlashScope().success("postnewitemandanswersok");
            return Results.json().render(item);

        } catch (NullPointerException e) {
            context.getFlashScope().error("postnewitemandanswersfail");
            return Results.text().renderRaw(this.getMsg("item.postNewItemAndAnswersFail", context));
        }

    }

    public Result postUpdateItemAndAnswersJson(@Param("itemid") String itemid,
            @Param("itemstring") String itemstring,
            @Param("itemdifficulty") String itemdifficulty,
            @Param("answerstring1") String answerstring1,
            @Param("answercorrect1") String answercorrect1,
            @Param("answerstring2") String answerstring2,
            @Param("answercorrect2") String answercorrect2,
            @Param("answerstring3") String answerstring3,
            @Param("answercorrect3") String answercorrect3,
            Context context) {

        try {

            Long itemidl = Long.parseLong(itemid);
            Short itemdifficultysi = Short.parseShort(itemdifficulty);
            Boolean answercorrect1bool = Boolean.parseBoolean(answercorrect1);
            Boolean answercorrect2bool = Boolean.parseBoolean(answercorrect2);
            Boolean answercorrect3bool = Boolean.parseBoolean(answercorrect3);

            Titem item = itemDao.getItemById(itemidl);

            item.setItemstring(itemstring);
            item.setItemdifficulty(itemdifficultysi);

            List<Tanswer> answers = (List<Tanswer>) item.getTanswerCollection();
            answers.get(0).setAnswerstring(answerstring1);
            answers.get(0).setAnswercorrect(answercorrect1bool);
            answers.get(1).setAnswerstring(answerstring2);
            answers.get(1).setAnswercorrect(answercorrect2bool);
            answers.get(2).setAnswerstring(answerstring3);
            answers.get(2).setAnswercorrect(answercorrect3bool);

            item.setTanswerCollection(answers);

            itemDao.postUpdateItem(item);

            context.getFlashScope().success("postupdateitemandanswersok");
            return Results.json().render(item);

        } catch (NullPointerException e) {
            context.getFlashScope().error("postupdateitemandanswersfail");
            return Results.text().renderRaw(this.getMsg("item.postUpdateItemAndAnswersFail", context));
        }

    }

    public Result deleteItem(@PathParam("itemid") String itemid,
            Context context) {

        Long itemidl = Long.parseLong(itemid);
        Boolean ok = itemDao.deleteItem(itemidl);

        if (ok) {
            context.getFlashScope().success("deleteitemok");
            return Results.text().renderRaw(this.getMsg("item.deleteItemOk", context));
        } else {
            context.getFlashScope().success("deletegroupfail");
            return Results.text().renderRaw(this.getMsg("item.deleteItemFail", context));
        }

    }

}
