package cjcx.service;

import cjcx.entity.Cj;
import cjcx.entity.DiplomaForm;
import cjcx.entity.Exam;
import cjcx.entity.User;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Administrator on 2017/1/28.
 */
public interface ExcelService {

    public List<Cj> readExcel(InputStream fileInputStream) throws IOException, BiffException, ParseException;

    public List<DiplomaForm> readDiplomaExcel(InputStream fileInputStream) throws IOException, BiffException, ParseException;

    public byte[] writerExcel(User user, List<Cj> cjs, Exam exam, String[] title) throws IOException, WriteException;


}
