import cjcx.entity.Cj;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/2/9.
 */
public class JsonTest {

    @Test
    public void testJson(){
//         String str = \"[{\\"idNumber\\":\\"522421196403180814\\",\\"studentName\\":\\"石利\\",\\"examDate\\":null,\\"batchNumber\\":\\"\\",\\"company\\":\\"毕节市新禹水利建筑工程有限公司\\",\\"id\\":0,\\"grades\\":[{\\"id\\":0,\\"cj\\":null,\"+
//                \"\\"test\\":{\\"id\\":20,\\"name\\":\\"施工企业成绩\\"},\\"socre\\":59},{\\"id\\":0,\\"cj\\":null,\\"test\\":{\\"id\\":22,\\"name\\":\\"专业能力成绩\\"},\\"socre\\":60}],\"+
//        \"\\"exam\\":{\\"number\\":\\"111\\",\\"name\\":\\"全国水利安全生产标准化考试\\",\\"tests\\":[{\\"id\\":20,\\"name\\":\\"施工企业成绩\\"},{\\"id\\":21,\\"name\\":\\"项目法人成绩\\"},{\\"id\\":22,\\"name\\":\\"专业能力成绩\\"},{\\"id\\":19,\\"name\\":\\"水管单位成绩\\"}],\\"listTests\\":null}}]\";
        String str = "[{\"idNumber\":\"522421196403180814\",\"studentName\":\"石利123\",\"examDate\":null,\"batchNumber\":\"\",\"company\":\"毕节市新禹水利建筑工程有限公司\",\"id\":0,\"grades\":[{\"id\":0,\"cj\":null,\"test\":{\"id\":22,\"name\":\"专业能力成绩\"},\"socre\":60},{\"id\":0,\"cj\":null,\"test\":{\"id\":20,\"name\":\"施工企业成绩\"},\"socre\":59}],\"exam\":{\"number\":\"111\",\"name\":\"全国水利安全生产标准化考试\",\"tests\":[{\"id\":22,\"name\":\"专业能力成绩\"},{\"id\":19,\"name\":\"水管单位成绩\"},{\"id\":20,\"name\":\"施工企业成绩\"},{\"id\":21,\"name\":\"项目法人成绩\"}],\"listTests\":null},\"grade0\":\"\",\"grade1\":\"\",\"grade2\":\"\",\"grade3\":\"\"},{\"idNumber\":\"522421196103030830\",\"studentName\":\"周光凯123\",\"examDate\":null,\"batchNumber\":\"\",\"company\":\"毕节市新禹水利建筑工程有限公司\",\"id\":0,\"grades\":[{\"id\":0,\"cj\":null,\"test\":{\"id\":22,\"name\":\"专业能力成绩\"},\"socre\":60},{\"id\":0,\"cj\":null,\"test\":{\"id\":20,\"name\":\"施工企业成绩\"},\"socre\":66}],\"exam\":{\"number\":\"111\",\"name\":\"全国水利安全生产标准化考试\",\"tests\":[{\"id\":22,\"name\":\"专业能力成绩\"},{\"id\":19,\"name\":\"水管单位成绩\"},{\"id\":20,\"name\":\"施工企业成绩\"},{\"id\":21,\"name\":\"项目法人成绩\"}],\"listTests\":null},\"grade0\":\"\",\"grade1\":\"\",\"grade2\":\"\",\"grade3\":\"\"},{\"idNumber\":\"51010219750310243X\",\"studentName\":\"何永光123\",\"examDate\":null,\"batchNumber\":\"\",\"company\":\"毕节市新禹水利建筑工程有限公司\",\"id\":0,\"grades\":[{\"id\":0,\"cj\":null,\"test\":{\"id\":22,\"name\":\"专业能力成绩\"},\"socre\":60},{\"id\":0,\"cj\":null,\"test\":{\"id\":20,\"name\":\"施工企业成绩\"},\"socre\":51}],\"exam\":{\"number\":\"111\",\"name\":\"全国水利安全生产标准化考试\",\"tests\":[{\"id\":22,\"name\":\"专业能力成绩\"},{\"id\":19,\"name\":\"水管单位成绩\"},{\"id\":20,\"name\":\"施工企业成绩\"},{\"id\":21,\"name\":\"项目法人成绩\"}],\"listTests\":null},\"grade0\":\"\",\"grade1\":\"\",\"grade2\":\"\",\"grade3\":\"\"}]";
        System.out.println(str);
        List<Cj> cjs = JSON.parseArray(str, Cj.class);
        System.out.println(cjs);
    }
}
