import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PollModule, voteByOptRes } from './poll-module';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})                                                                                                                                                                                                                                                                                                                                                                                            
export class PollService {
  constructor(private http:HttpClient){}
  private baseUrl:string="http://localhost:8081/api/v1/poll/";

  getPolls():Observable<PollModule[]>{
    return this.http.get<PollModule[]>(this.baseUrl);
  }

  createPoll(poll:PollModule):Observable<PollModule>{
    return this.http.post<PollModule>(this.baseUrl,poll);
  }

  voteByOption(pollID:number,option:string){
    //encodeURIComponent() is used to encode data. As URL not allow any special characters. 
    // That's why if we are sending C++ from Angular it's becomes "C  ". 
    // To avoid this we used this encoder. "C++" will be converted into "C%25%25".
    //In springboot, again it will autoconverted into "C++"
    let url=`${this.baseUrl}${pollID}/vote?option=${encodeURIComponent(option)}`
    console.log(url);
    
   // this.baseUrl.concat(pollID.toString()).concat("/vote?option=").concat(option);
    return this.http.post<voteByOptRes>(url,null);
  }
}
