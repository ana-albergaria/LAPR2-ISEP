package app.domain.model;

import org.junit.Test;

public class OrgRoleTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureDescriptionNotEmpty(){
        OrgRole instance = new OrgRole("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureDescriptionNotNull(){
        OrgRole instance = new OrgRole(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureDescriptionNoLongerThan15Chars(){
        OrgRole instance = new OrgRole("The Person Who Talks To The Clients");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureDescriptionHasOnlyLetters(){
        OrgRole instance = new OrgRole("R3c3pc10n1st");
    }

}