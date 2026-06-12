import { ChangeDetectorRef, Component } from '@angular/core';
import { PollService } from '../poll';
import { Options, PollModule } from '../poll-module';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-poll',
  imports: [CommonModule],
  templateUrl: './poll.html',
  styleUrl: './poll.css',
})
export class Poll {
  polls:PollModule[]=[];
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
        console.log(data)
        // this.polls.forEach((p:PollModule)=>{
        //   if(p.id===data.pollId){
        //     p.options.forEach((o:Options)=>{
        //       if(o.option.match(data.option)){
        //         o.votes=data.vote;
        //       }
        //     })
        //   }
        // })
        // this.cdr.detectChanges();
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
}
