% knowledge base for moh

% BMI WEIGHT CATEGORIES
% bmi facts

underweight([18.5]). % below 
normalweight([18.5,24.9]). % over and less than 24.9
overweight([25,29.9]) . % 25 - 29.9	Overweight
obese(30.0). % over 30


% BMI Calculations
% bmi rules
printHeight(Height):- write("Height in meters =  "), nl,(format('~3f',[Height])),classify_bmi(Height).

convertToM(CalcMeters):- CalcM is (CalcMeters / 100).

convertToCm(CalcFeetInches):- CalcCm is (CalcFeetInches * 2.540),convertToM(CalcCm).

calculate_height(Feet,Inches):- 
 CalcFeet is (Feet * 12 ), CalcFeetInches is 
 (CalcFeet + Inches), convertToCm(CalcFeetInches).
 
 % calculates the body mass index of the individual, R is Height squared.
 calculate_bmi(Height,Weight):-power(Height,2,R) , Bmi is (WEIGHT divs R).
 
 %calculate kilograms weight to pounds
 calculate_weight(Weight):- Pounds is (Weight * 2.20462).
 
 
 
 % bmi classification based on height(meters) and weight(pounds).
classify_bmi(Bmi):-(Bmi >= 30.0 -> Status = 'Obese'; Bmi < 18.5 -> 
Status = 'UnderWeight'; Bmi >= 18.5 ,
 Bmi =< 24.9 -> Status = 'NormalWeight';
 Bmi >= 25 , Bmi =< 29.9 -> Status = 'OverWeight'
),nl,write(Status).


input(Feet,Inches,Weight):-
calculate_height(Feet,Inches),
calculate_weight(Weight),
calculate_bmi(CalcM,Pounds),
classify_bmi(Bmi). 

