import org.testng.annotations.Test;

import Base.BaseClass;
import PageClass.AuthenticationOperations;

public class AuthenticationOperationsTest extends BaseClass{
    public AuthenticationOperationsTest(){
        super();
    }

    AuthenticationOperations authOpearations;

    @Test(priority=1)
    public void userAuthentications(){
       authOpearations = new AuthenticationOperations(getDriver());
       authOpearations.UserSignin("7042579843", "Ashu@1997");
    }
}
