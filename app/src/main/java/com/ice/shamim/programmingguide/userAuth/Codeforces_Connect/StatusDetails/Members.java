package com.ice.shamim.programmingguide.userAuth.Codeforces_Connect.StatusDetails;

public class Members
{
    private String handle;

    public String getHandle ()
    {
        return handle;
    }

    public void setHandle (String handle)
    {
        this.handle = handle;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [handle = "+handle+"]";
    }
}
