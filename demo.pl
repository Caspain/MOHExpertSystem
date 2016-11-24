
dynamic data. % contains dynamic user data

% responsible for reading all stored user data.
main :-
    open('expert_db.txt', read, Str),
    read_file(Str,Lines),
    close(Str).
	
% check if end of file is reached.
read_file(Stream,[]) :-
    at_end_of_stream(Stream).
	
% keep reading lines if no end of file.
read_file(Stream,[X|L]) :-
    \+ at_end_of_stream(Stream),
    read(Stream,X),
	assert(data(X)),
    read_file(Stream,L).  
	  
	  maintain(Name,Age,Origin,Status,Height,Weight) :-
		assert(data(user(user_bmi_type(Type,user_name(Name)),
			user_age(Age),
			user_weight(Weight),
			user_ethnicity(Origin),
			user_height(H)))).
			
			
 file_write(Name,Age,Origin,Type,Height,Weight) :-
      format(atom(H),'~3f',[Height]),
		 open('expert_db.txt',append,Stream),
		 write(Stream,
		 'user(user_bmi_type(Type,user_name(Name)),user_age(Age),user_weight(Weight),user_ethnicity(Origin),user_height(H)).'),
		     close(Stream).
			 
take :-
retractall(data).

loop_data(Name) :-
  data(user(user_bmi_type(_,user_name(Name)),user_age(_),user_weight(_),user_ethnicity(_),user_height(_))),concatlist([Name],[],L),write(L),fail.
  
 
  
    concatlist([],List2,List2).
    concatlist([Item|Tail],List2,[Item|Newlist]) :-
	concatlist(Tail,List2,Newlist).
  
			 