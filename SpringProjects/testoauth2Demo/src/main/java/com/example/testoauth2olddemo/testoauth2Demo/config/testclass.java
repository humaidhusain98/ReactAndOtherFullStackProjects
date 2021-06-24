package com.example.testoauth2olddemo.testoauth2Demo.config;

import java.util.ArrayList;
import java.util.List;

public class testclass {
    public static void main(String args[])
    {
        searchJob searchJobObj=new searchJob();

        //LOCATIONS
        List<String> locations=new ArrayList<>();
        locations.add("Kolkata");
        locations.add("Delhi");
        locations.add("Mumbai");
        searchJobObj.setLocations(locations);
        String LocationsQuery="";
        if(searchJobObj.getLocations()!=null)
        {
            int flag=0;
            for(int i=0;i<searchJobObj.getLocations().size();i++)
            {
                if(flag==0)
                {
                    LocationsQuery="city ilike '%"+searchJobObj.getLocations().get(i)+"%'";
                    flag=1;
                }
                else
                {
                    LocationsQuery= LocationsQuery+" OR"+" city ilike '%"+searchJobObj.getLocations().get(i)+"%'";
                }

            }

        }
        System.out.println(LocationsQuery);

        String CompanyQuery="";
        List<String> companynames=new ArrayList<>();
        companynames.add("Amazon");
        companynames.add("Flipkart");
        companynames.add("Google");
        searchJobObj.setCompanyNames(companynames);

        if(searchJobObj.getCompanyNames()!=null)
        {
            int flag=0;
            for(int i=0;i<searchJobObj.getCompanyNames().size();i++)
            {
                if(flag==0)
                {
                    CompanyQuery="companyname ilike '%"+searchJobObj.getCompanyNames().get(i)+"%'";
                    flag=1;
                }
                else
                {
                    CompanyQuery= CompanyQuery+" OR"+" companyname ilike '%"+searchJobObj.getCompanyNames().get(i)+"%'";
                }

            }

        }
        System.out.println(CompanyQuery);


        String CompetencyQuery="";
        List<String> competency=new ArrayList<>();
        competency.add("PYTHON");
        competency.add("Java");
        competency.add("C++");
        searchJobObj.setCompetencies(competency);

        if(searchJobObj.getCompetencies()!=null)
        {
            int flag=0;
            for(int i=0;i<searchJobObj.getCompetencies().size();i++)
            {
                if(flag==0)
                {
                    CompetencyQuery="competencytag ilike '%"+searchJobObj.getCompetencies().get(i)+"%'";
                    flag=1;
                }
                else
                {
                    CompetencyQuery= CompetencyQuery+" OR"+" competencytag ilike '%"+searchJobObj.getCompetencies().get(i)+"%'";
                }

            }

        }
        System.out.println( CompetencyQuery);

        String SalaryQuery =" salarystart>="+searchJobObj.getMinSal()+" AND salaryend<="+searchJobObj.getMaxSal();
        String ExperienceQuery = " AND expstart>="+searchJobObj.getMinExp()+" AND expend<="+searchJobObj.getMaxExp();
//        System.out.println(SalaryQuery);
//        System.out.println(ExperienceQuery);

        String finalQuery="";

        if((searchJobObj.getLocations()==null) && (searchJobObj.getCompetencies()==null) && (searchJobObj.getCompanyNames() == null))
        {
             finalQuery="SELECT * FROM jobs WHERE "+SalaryQuery+ExperienceQuery;
        }
        else if((searchJobObj.getLocations()==null) && (searchJobObj.getCompetencies()==null) )
        {
            finalQuery="SELECT * FROM jobs WHERE "+SalaryQuery+ExperienceQuery+" AND ("+CompanyQuery+")";
        }
        else if((searchJobObj.getLocations()==null) && (searchJobObj.getCompanyNames() == null))
        {
            finalQuery="SELECT * FROM jobs WHERE "+SalaryQuery+ExperienceQuery+" AND ("+CompetencyQuery+")";
        }
        else if((searchJobObj.getCompetencies()==null) && (searchJobObj.getCompanyNames() == null))
        {
            finalQuery="SELECT * FROM jobs WHERE "+SalaryQuery+ExperienceQuery+" AND ("+LocationsQuery+")";
        }
        else if((searchJobObj.getLocations()==null))
        {
            finalQuery="SELECT * FROM jobs WHERE "+SalaryQuery+ExperienceQuery+" AND ("+CompanyQuery+") AND ("+CompetencyQuery+")";
        }
        else if((searchJobObj.getCompetencies()==null))
        {
            finalQuery="SELECT * FROM jobs WHERE "+SalaryQuery+ExperienceQuery+" AND ("+CompanyQuery+") AND ("+LocationsQuery+")";
        }
        else if((searchJobObj.getCompanyNames() == null))
        {
            finalQuery="SELECT * FROM jobs WHERE "+SalaryQuery+ExperienceQuery+" AND ("+CompetencyQuery+") AND ("+LocationsQuery+")";
        }
        else
        {
            finalQuery="SELECT * FROM jobs WHERE "+SalaryQuery+ExperienceQuery+" AND ("+CompetencyQuery+") AND ("+LocationsQuery+") AND ("+CompanyQuery+")";
        }







        System.out.println(finalQuery);




    }

}
