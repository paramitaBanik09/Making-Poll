export interface PollModule{
  "id":number;
  "question":string;
  "options":Options[];
}

export interface Options{
  "option":string;
  "votes":number;
}
