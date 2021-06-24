package com.people.chat.Model;


import java.util.List;


public class searchJob {
     List<String> competencies;
     List<String> locations;
     List<String> companyNames;
     int minSal=0;
     int maxSal=100;
     int minExp=0;
     int maxExp=100;
     int page=1;
     int pagesize=10;
     //Default Values already given
     public List<String> getCompanyNames() {
          return companyNames;
     }

     public void setCompanyNames(List<String> companyNames) {
          this.companyNames = companyNames;
     }

     public List<String> getCompetencies() {
          return competencies;
     }

     public void setCompetencies(List<String> competencies) {
          this.competencies = competencies;
     }

     public List<String> getLocations() {
          return locations;
     }

     public void setLocations(List<String> locations) {
          this.locations = locations;
     }

     public int getMinSal() {
          return minSal;
     }

     public void setMinSal(int minSal) {
          this.minSal = minSal;
     }

     public int getMaxSal() {
          return maxSal;
     }

     public void setMaxSal(int maxSal) {
          this.maxSal = maxSal;
     }

     public int getMinExp() {
          return minExp;
     }

     public void setMinExp(int minExp) {
          this.minExp = minExp;
     }

     public int getMaxExp() {
          return maxExp;
     }

     public void setMaxExp(int maxExp) {
          this.maxExp = maxExp;
     }

     public int getPage() {
          return page;
     }

     public void setPage(int page) {
          this.page = page;
     }

     public int getPagesize() {
          return pagesize;
     }

     public void setPagesize(int pagesize) {
          this.pagesize = pagesize;
     }

     public searchJob() {
     }

     @Override
     public String toString() {
          return "searchJob{" +
                  "competencies=" + competencies +
                  ", locations=" + locations +
                  ", companyNames=" + companyNames +
                  ", minSal=" + minSal +
                  ", maxSal=" + maxSal +
                  ", minExp=" + minExp +
                  ", maxExp=" + maxExp +
                  ", page=" + page +
                  ", pagesize=" + pagesize +
                  '}';
     }
}
