

%----------------------------------------------------------
/*
The Ministry of Health (MOH) has embarked on 
some programs/initiatives aimed at 
identifying and controlling certain
 lifestyle diseases such as diabetes.
 The Director of the MOH highlighted
 the need to educate the public about
 these diseases and has made the move 
 to partner with the school of computing 
 at the University 
of Technology Jamaica (UTECH) to 
create an Expert system that 
could assist the MOH in its efforts
*/

% knowledge base for moh

% BMI WEIGHT CATEGORIES
% bmi facts

underweight([18.5]). % below 
normalweight([18.5,24.9]). % over and less than 24.9
overweight([25,29.9]) . % 25 - 29.9	Overweight
obese([30.0]). % over 30


% BMI Calculations
% bmi rules
% printHeight(Height):- write("Height in meters =  "), nl,(format('~3f',[Height])),classify_bmi(Height).

convertToM(CalcMeters,Height):- CalcM is (CalcMeters / 100),Height is CalcM .

convertToCm(CalcFeetInches,Height):- CalcCm is (CalcFeetInches * 2.540), convertToM(CalcCm,Height).

calculate_height(Feet,Inches,Height):- 
 CalcFeet is (Feet * 12 ), CalcFeetInches is 
 (CalcFeet + Inches), convertToCm(CalcFeetInches,Height).
 
 % calculates the body mass index of the individual, R is Height squared.
 calculate_bmi(Height,Weight,Bmi):-nl, R is (Height * Height) , Bmi is (Weight / R).
 
 %calculate  pounds weight to kilogram.
 calculate_weight(Weight,Kilogram):- Kilogram is (Weight * 0.453592).
 
 %write to file
 file_write(Name,Age,Origin,Type,Height,Weight):-
      format(atom(H),'~3f',[Height]),
		 open('expert_db.txt',append,Stream),
		 write(Stream,
		 user(user_bmi_type(Type,user_name(Name)),
			user_age(Age),
			user_weight(Weight),
			user_ethnicity(Origin),
			user_height(H))),nl(Stream),
			      
		 close(Stream).
		
 
 % bmi classification based on height(meters) and weight(pounds).
classify_bmi(Bmi,Name,Age,Origin,Height,Weight):- nl ,(
Bmi >= 30.0 -> Status = 'Obese'; 
Bmi < 18.5 -> 
Status = 'UnderWeight'; Bmi >= 18.5 ,
 Bmi =< 24.9 -> Status = 'NormalWeight';
 Bmi >= 25 , Bmi < 30 -> Status = 'OverWeight'
),nl, file_write(Name,Age,Origin,Status,Height,Weight).


bmi_input(Name,Age,Weight,Origin,Feet,Inches,WaistCir,ExerAmt,VegFruits,HighBP,HighBG,Gender,Category):-
calculate_height(Feet,Inches,Height), % returned Height in meters.
calculate_weight(Weight,Kilogram),  % returned weight in pounds.
calculate_bmi(Height,Kilogram,Bmi), % returns individual calculated body mass index.
% intercept shervain files here
format(atom(H),'~3f',[Height]), % up to three decimal places...
update_database(Gender, Age, Weight, H, WaistCir, ExerAmt, VegFruits, HighBP, HighBG, Category,Bmi),
classify_bmi(Bmi,Name,Age,Origin,Height,Kilogram). % later you can persist





% defines the format for user input variables
% facts
user_name(Name).
user_age(Age).
user_weight(Weight).
user_ethnicity(Origin).
user_height(Height).
bmi_type([obese,underweight,overweight,normalweight]).

user_bmi_type(Type,user_name(Name)).

% facts about a user

user(user_bmi_type(Type,user_name(Name)),
user_age(Age),
user_weight(Weight),
user_ethnicity(Origin),
user_height(Height)).


% inputs and test 
test_user_data(Name,Age,Weight,Origin,Feet,Inches,WaistCir,ExerAmt,VegFruits,HighBP,HighBG,Gender,Category):-
bmi_input(Name,Age,Weight,Origin,Feet,Inches,WaistCir,ExerAmt,VegFruits,HighBP,HighBG,Gender,Category). % calculate respective bmi classifiers.


%----------------------------------------------------------
:-use_module(library(csv)).


:- dynamic record/13.
:- dynamic test/2.

%% The different risk levels for type 2 diabetes
risk(sym(low), name("Low")).
risk(sym(slightly_elevated), name("Slightly Elevated")).
risk(sym(moderate), name("Moderate")).
risk(sym(high), name("High")).
risk(sym(very_high), name("Very High")).

%% the recommendations based on risk level
risk_treatment(low, ['X', 'Y', 'Z']).
risk_treatment(slightly_elevated, ['A', 'B', 'C']).
risk_treatment(moderate, ['L', 'M', 'N']).
risk_treatment(high, ['H', 'I', 'J']).
risk_treatment(very_high, ['Q', 'R', 'S']).

%% filters high or very high risk records
filter_high_risk([],[]).
filter_high_risk([H|T],[H|NT]):- (H == 'Very High'; H == 'High'), filter_high_risk(T,NT).
filter_high_risk([_|T],Result):- filter_high_risk(T,Result).

%% filters persons with a family history of type 1 or type 2 diabetes
filter_fam_history([],[]).
filter_fam_history([H|T],[H|NT]):- (H == 1; H == 2), filter_fam_history(T,NT).
filter_fam_history([_|T],Result):- filter_fam_history(T,Result).

%% sum the contents of a list
sumList([], 0).
sumList([H|T],S) :- sumList(T, NS), S is H + NS.

%% finds the maximum element of a list
maxList([E],E).
maxList([H|T],Y):- maxList(T,Y), H < Y,!.
maxList([H|_],H).

%% finds the minimum element of a list
minList([E],E).
minList([H|T],Y):- minList(T,Y), H > Y,!.
minList([H|_],H).

%% determine how many points to give based on age
age_points(Age, Points):-
    (Age < 45 ->   Points is 0;
	(Age =< 54 ->   Points is 2;
	    Age =< 64 ->   Points is 3;
		Points is 4
        )
    ).

%% determine points based on bmi
bmi_points(BMI, Points):-
    BMI =< 25 ->   Points is 0;
	BMI =< 30 ->   Points is 1;
		Points is 3.

%% determine points based on waist circumference
waist_circumference_points(WaistCir, Gender, Points):-
    % males and females have different point levels
    (Gender == 'Male' ->   (WaistCir =< 94 ->	Points is 0;
			        WaistCir =< 102 ->   Points is 3;
			           Points is 4
			   );
    (WaistCir =< 80 ->   Points is 0;
    WaistCir =< 88 ->   Points is 3;
    Points is 4
                        )
    ).

%% determine points based on whether a person exercise atleast 30 mins
%% everyday
exercise_points(ExerciseAmt, Points):-
    ExerciseAmt == 'Yes' ->   Points is 0;  Points is 2.

%% determine points based on if vegs and fruits are eaten everyday
veggies_points(VegFreq, Points):-
    VegFreq == 'Yes' ->   Points is 0;	Points is 1.

%% determine points based on if a person has regularly taken high blood
%% pressure medication
high_bp_med_points(TakenMeds, Points):-
    TakenMeds == 'Yes' ->   Points is 2; Points is 0.

%% determine points based on if a person has suffered from high blood
%% glucose in the past
high_blood_glucose_points(HighBldGluc, Points):-
    HighBldGluc = 'Yes' ->   Points is 5; Points is 0.



% calculate points for family history of diabetes
diabetes_history_points(Category, Points):-
    Category == 0 ->   Points is 0;
	Category == 1 ->   Points is 3;
		Points is 5.
/*
% calculates body mass index
calculate_bmi(WeightKg, HeightM, BMI):-
    BMI is (WeightKg / (HeightM * HeightM)).
*/
%%%%% Counts the number of records that are within the database %%%%%
stat_num_records(Count):-
	findall(_, record(_,_,_,_,_,_,_,_,_,_,_,_,_), L),
	length(L, Count).

%%%%% Counts the number of High risk or Very High risk records %%%%%
stat_num_high_risk(Count):-
	findall(Risk, record(_,_,_,_,_,_,_,_,_,_,_,_, Risk), List),
	filter_high_risk(List, Filtered),
	length(Filtered, Count).

%%%%% Calculates average bmi of all records %%%%%
stat_avg_bmi(AvgBMI):-
	findall(BMI, record(_,_,_,_,_,_,_,_,_,_,BMI,_,_), BMIList),
	sumList(BMIList, Sum),
	stat_num_records(Count),
	AvgBMI is Sum / Count.

%%%%% Calculates average number of points for all records %%%%%
stat_avg_points(AvgPoints):-
	findall(Points, record(_,_,_,_,_,_,_,_,_,_,_,Points,_), PointsList),
	sumList(PointsList, Sum),
	stat_num_records(Count),
	AvgPoints is Sum / Count.

%%%%% Finds the maximum points a client scored from the database %%%%%
stat_max_points(MaxPoints):-
	findall(Points, record(_,_,_,_,_,_,_,_,_,_,_,Points,_), PointsList),
	maxList(PointsList, MaxPoints), !.

%%%%% Finds the minimum points a client scored in the assesment %%%%%
stat_min_points(MinPoints):-
	findall(Points, record(_,_,_,_,_,_,_,_,_,_,_,Points,_), PointsList),
	minList(PointsList, MinPoints), !.

%%%%% Calculates average age of all patient records %%%%%
stat_avg_age(AvgAge):-
	findall(Age, record(_,Age,_,_,_,_,_,_,_,_,_,_,_), AgeList),
	sumList(AgeList, Sum),
	stat_num_records(Count),
	AvgAge is Sum / Count.

%%%%% finds oldest age in the records %%%%%
stat_max_age(MaxAge):-
	findall(Age, record(_,Age,_,_,_,_,_,_,_,_,_,_,_), AgeList),
	maxList(AgeList, MaxAge), !.

%%%%% finds youngest age in the records %%%%%
stat_min_age(MinAge):-
	findall(Age, record(_,Age,_,_,_,_,_,_,_,_,_,_,_), AgeList),
	minList(AgeList, MinAge), !.

%%%%% finds records of patients with a family history of diabetes %%%%%
stat_family_history(HistoryCount):-
	findall(History, record(_,_,_,_,_,_,_,_,_,History,_,_,_), HistoryList),
	filter_fam_history(HistoryList, FilteredList),
	length(FilteredList, HistoryCount), !.

%% determine if a family members have been diagnosed
%% with type 1 or type2 diabetes
family_history(Category):-
	write("Have any of the members of your immediate family or other"),
    write("relatives been diagnosed with diabetes (type 1 or type 2)"),
    write("Select a category from the list below: "), nl,
    write("0 - No"), nl,
    write("1 - Yes (grandparent, aunt, uncle or cousins)"), nl,
    write("2 - Yes (parent, sibling, child)"),
    read(Answer),
    Category is Answer.

% determine risk of developing type 2 diabetes
calculate_diabetes_risk(TotalPoints, Risk):-
    nl, nl, nl, write("Your risk of developing Type 2 Diabetes with 10 years is: "),
    (TotalPoints < 7 ->   Risk = "Low";
	TotalPoints =< 11 ->   Risk = "Slighly Elevated";
		TotalPoints =< 14 ->   Risk = "Moderate";
			TotalPoints =< 20 ->   Risk = "High";
				Risk = "Very High"),
    write(Risk), nl.

% shows the recommendations based on risk level
show_recommendations(Risk):-
    write("Recommnedations for risk level ["), write(Risk), write("]: "),
    risk(sym(RiskLevel), name(RiskName)),
    RiskName == Risk,
    risk_treatment(RiskLevel, Treatments),
    write(Treatments), nl.

% generate alert regarding risk levels
generate_alert(Trigger):-
	stat_num_records(CountAll),
	stat_num_high_risk(CountHighRisk),
	(CountAll == 0 -> Percentage is 0; Percentage is (CountHighRisk / CountAll) * 100),
	Percentage >= 75 -> Trigger is 1; Trigger is 0.

%%%%% deletes all the items from the database
%%%%% repopulates it from the file that was persisted
load_database:-
	retractall(record(_,_,_,_,_,_,_,_,_,_,_,_,_)),
	csv_read_file('database.csv', Rows, [functor(record), arity(13)]),
	maplist(assert, Rows).

%%%%% calculates values for new patient record %%%%%
% modified 
update_database(Gender, Age, Weight, Height, WaistCir, ExerAmt, VegFruits, HighBP, HighBG, Category,BMI):-
	% calculate_bmi(Weight, Height, BMI),
	age_points(Age, APoints),
	bmi_points(BMI, BPoints),
	waist_circumference_points(WaistCir, Gender, WPoints),
	exercise_points(ExerAmt, EPoints),
	veggies_points(VegFruits, VPoints),
	high_bp_med_points(HighBP, BPPoints),
	high_blood_glucose_points(HighBG, BGPoints),
	diabetes_history_points(Category, HPoints),

	TotalPoints is (APoints + BPoints + WPoints + EPoints + VPoints + BPPoints + BGPoints + HPoints),
	calculate_diabetes_risk(TotalPoints, Risk),
	show_recommendations(Risk),

	% add data to database and persist
	assert(record(Gender, Age, Weight, Height, WaistCir, ExerAmt, VegFruits, HighBP, HighBG, Category, BMI, TotalPoints, Risk)),
	% write to file
	update_db_file.

% updates the database file
% this writes the entire db to the file each time
% TODO: update to be more efficient
update_db_file:-
	use_module(library(csv)),
	findall(row(A,B,C,D,E,F,G,H,I,J,K,L,M), record(A,B,C,D,E,F,G,H,I,J,K,L,M), Records),
	csv_write_file('database.csv', Records).


%%%%% run the program in the console
% first call test_user_data/6 predicate
/*
run_program:-
      %test_user_data(Name,Age,Weight,Origin,Feet,Inches,WaistCir,ExerAmt,VegFruits,HighBP,HighBG)/12
	  % include gender later.
	  % include category later
      % include alert later
	  
    write("Welcome to the MOH Expert System."), nl,
    write("Correctly answer the following questions to get your Type 2 diabetes diagnosis."), nl, nl,

    %% alert the user if number of records at high risk or above is over 75%
    generate_alert(ShowAlert),
    (ShowAlert == 1 -> (write("ALERT: Over 75% of database records are [HIGH/VERY HIGH] risk for Type 2 Diabetes."), nl, nl); nl),
    write("What is your gender: "), read(Gender), nl,

    write("What is your age: "), read(Age), nl,
    write("What is your weight(kg): "), read(Weight), nl,
    write("What is your height(m): "), read(Height), nl,
    write("What is your waist circumference(cm): "), read(WaistCir), nl,
    write("Do you exercise atleast 30 minutes every day(Yes/No): "), read(ExerAmt), nl,
    write("Do you eat vegetables or fruits every day(Yes/No): "), read(VegFruits), nl,
    write("Have you ever taken medication for high blood pressure on regular basis(Yes/No): "), read(HighBP), nl,
    write("Have you ever been found to have high blood glucose(Yes/No): "), read(HighBG), nl,
    family_history(Category),
    update_database(Gender, Age, Weight, Height, WaistCir, ExerAmt, VegFruits, HighBP, HighBG, Category).
*/
engine(Name,Age,Weight,Origin,Feet,Inches,WaistCir,ExerAmt,VegFruits,HighBP,HighBG,Gender,Category):-
% prompt user for input
    test_user_data(Name,Age,Weight,Origin,Feet,Inches,WaistCir,ExerAmt,VegFruits,HighBP,HighBG,Gender,Category).
	