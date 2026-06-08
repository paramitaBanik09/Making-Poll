import { ChangeDetectorRef, Component } from '@angular/core';
import { PollService } from '../poll';
import { PollModule } from '../poll-module';
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
    this.pollService.voteByOption(pollId,option).subscribe({
      next:(data)=>{
        console.log(data)
        this.loadPolls()
        this.cdr.detectChanges();
      },
      error: (error)=>{
        console.error("Error!!",error)
      }
    })
  }
}
