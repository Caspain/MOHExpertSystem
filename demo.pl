
dynamic data.
  
main :-
    open('expert_db.txt', read, Str),
    read_file(Str,Lines),
    close(Str),
     nl.

read_file(Stream,[]) :-
    at_end_of_stream(Stream).

read_file(Stream,[X|L]) :-
    \+ at_end_of_stream(Stream),
    read(Stream,X),
	assert(data(X)),
	nl,
    read_file(Stream,L).  
	  