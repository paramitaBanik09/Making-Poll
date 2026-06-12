export interface PollModule{
  "id":number;
  "question":string;
  "options":Options[];
}

export interface Options{
  "option":string;
  "votes":number;
}

export interface voteByOptRes{
  "pollId":number
  "option":string
  "vote":number
}