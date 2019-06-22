package com.ice.shamim.programmingguide.MainMenu.Fragment.Event.EventGamePlay;


public class EventTestModel
{
    private Result[] result;

    private String round;

    private String deadline;

    public Result[] getResult ()
    {
        return result;
    }

    public void setResult (Result[] result)
    {
        this.result = result;
    }

    public String getRound ()
    {
        return round;
    }

    public void setRound (String round)
    {
        this.round = round;
    }

    public String getDeadline ()
    {
        return deadline;
    }

    public void setDeadline (String deadline)
    {
        this.deadline = deadline;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [result = "+result+", round = "+round+", deadline = "+deadline+"]";
    }
}


