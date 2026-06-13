import { ChangeDetectorRef, Component } from '@angular/core';
import { PollService } from '../poll';
import { Options, PollModule } from '../poll-module';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-poll',
  imports: [CommonModule,FormsModule],
  templateUrl: './poll.html',
  styleUrl: './poll.css',
})
export class Poll {
  polls:PollModule[]=[];
  failuerAlert:string="";
  //noOFOption:number=0; 
  newPoll:PollModule={
    id:0,
    question:"",
    options:[
      {
        option:"", votes:0
      },
      {
        option:"", votes:0
      }
    ]
  }
  constructor(private pollService : PollService,private cdr: ChangeDetectorRef){}

  ngOnInit(){
    this.loadPolls();
  }

  loadPolls(){
    console.log("Calling API");
    
    this.pollService.getPolls().subscribe({
      next:(data)=>{
        console.log(data)
        this.polls=data;
        console.log(this.polls);
        this.cdr.detectChanges();
      },
      error: (error)=>{
        console.error("Error in featching poll: ",error)
      }
    })
  }

  onVoteClick(pollId:number,option:string){
    console.log(option);
    
    this.pollService.voteByOption(pollId,option).subscribe({
      next:(data)=>{
       let poll:PollModule | undefined= this.polls.find(p=>p.id===data.pollId);
       if(poll){
        let o:Options | undefined = poll.options.find(o=>o.option===data.option);
        if(o){
          o.votes=data.vote;
        }
        this.cdr.detectChanges();
       }
       
      },
      error: (error)=>{
        console.error("Error!!",error)
      }
    })
  }

  addOption(){
    this.newPoll.options.push({
      option:"",
      votes:0
    })
  }
  deleteButton(){
    this.newPoll.options.splice(1,1)
  }
  discartPoll(){
    this.newPoll={
      id:0,
      question:"",
      options:[
        {
          option:"",
          votes:0
        },
        {
          option:"",
          votes:0
        }
      ]
    }
  }
  createPoll(){
    this.pollService.createPoll(this.newPoll).subscribe({
      next:(addedPoll)=>{
        this.polls.push(addedPoll)
        this.newPoll={
          id:0,
          question:"",
          options:[
            {option:"",votes:0},
            {option:"",votes:0}
          ]
        }

        //this.successAlert="Poll created";
        this.cdr.detectChanges();
      },
      error:(err)=>{
        this.failuerAlert=err.error.message;
        this.cdr.detectChanges();
      }
    })
  }
  removeAlert(){
    this.failuerAlert="";
  }
}
