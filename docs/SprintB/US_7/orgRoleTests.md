##Organization role Tests

**Test 1:** Check it is not possible to create an instance of organization role with null or empty string values

**For example:**

    @Test(expected = IllegalArgumentException.class)
    public void ensureDescriptionNotNull(){
        OrgRole instance = new OrgRole(null);
    }
    
**Test 2:** Check it is not possible to create an organization role not following the current criterias.

> * **AC6:**  "Organization Role: a string with no more than 15 characters."

**For example:**

    @Test(expected = IllegalArgumentException.class)
    public void ensureDescriptionNoLongerThan15Chars(){
        OrgRole instance = new OrgRole("The Person Who Talks To The Clients");
    }
    
**Test 3:** Check if equals method is returning correct values for each equality

**Namely:**
>Same object: true
>Different object and same attributes: true
>Null: false
>Different object and different attributes: false

**For example:**

    @Test
    public void equalsTrue(){
        OrgRole role1 = new OrgRole("Recepcionist");
        OrgRole role2 = new OrgRole("Recepcionist");
        boolean result = role1.equals(role2);
        Assert.assertTrue(result);
    }