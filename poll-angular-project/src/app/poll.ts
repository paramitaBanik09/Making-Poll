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
    let url=`${this.baseUrl}${pollID}/vote?option=${encodeURIComponent(option)}`
    console.log(url);
    
   // this.baseUrl.concat(pollID.toString()).concat("/vote?option=").concat(option);
    return this.http.post<voteByOptRes>(url,null);
  }
}
