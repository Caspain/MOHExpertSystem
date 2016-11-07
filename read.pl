
main:-
 open('expert_db.txt',read,Str),
   read(Str,User),
   close(Str),
   write(User).