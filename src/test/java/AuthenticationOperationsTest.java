import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import Base.BaseClass;
import PageClass.AuthenticationOperations;
import utilities.ExcelUtilities;

public class AuthenticationOperationsTest extends BaseClass{
    public AuthenticationOperationsTest(){
        super();
    }

    AuthenticationOperations authOpearations;

    @DataProvider(name="authData")
    public Object[][] sendValues(){
        return new Object[][]{
            {"7042579843", "Ashu@1997"},
            {"9625525675", "Ashu@123"}
        };
    }

    @DataProvider(name="getData")
    public Object[][] getValuesFromExcel(){
        String excelPath = "D:\\Cursor-Workspace\\Selenium\\Amazon\\InputFiles\\userCreds.xlsx";
        ExcelUtilities excelUtilities = null;
        try{
            excelUtilities = new ExcelUtilities(excelPath, "sheet1");
            int rowCount = excelUtilities.getRowCount(); // last row index, header assumed at row 0
            Object[][] data = new Object[rowCount][2];

            for(int i=1; i<=rowCount; i++){
                data[i-1][0] = excelUtilities.getCellData(i, 0); // username
                data[i-1][1] = excelUtilities.getCellData(i, 1); // password
            }
            return data;
        }catch(IOException e){
            throw new RuntimeException("Unable to read test data from Excel: " + excelPath, e);
        }finally{
            if(excelUtilities != null){
                try{
                    excelUtilities.close();
                }catch(IOException ignored){
                    // no-op
                }
            }
        }
    }

    @Test(priority=1, dataProvider = "getData")
    public void userAuthentications(String uname, String pwd){
       authOpearations = new AuthenticationOperations(getDriver());
       authOpearations.UserSignin(uname, pwd);
    }
}
