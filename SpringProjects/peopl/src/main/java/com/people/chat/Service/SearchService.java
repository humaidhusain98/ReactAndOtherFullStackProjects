package com.people.chat.Service;

import java.util.List;

import com.people.chat.Model.User;
import com.people.chat.ResponseDto.SearchResponseDTO;

public interface SearchService {
    public List<SearchResponseDTO> getResultsFromCurrentCompany(Integer companyId, Integer pid);

    public List<SearchResponseDTO> getResultsFromCompetency(Integer compId, Integer exp, Integer pid); // search by
                                                                                                       // competency and
                                                                                                       // exp

    public List<SearchResponseDTO> getResultsFromCurrentProf(Integer profId, Integer pid, Integer yrs); // Search By
                                                                                                        // title and exp

    public List<SearchResponseDTO> getResultsFromCurrentLocation(String cityName, Integer pid);

    public List<SearchResponseDTO> getResultsFromName(String name, Integer pid);

    public List<SearchResponseDTO> getSearchDetails(List<User> userList, Integer pid);

}