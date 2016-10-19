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

convertToM(CalcMeters,Height):- CalcM is (CalcMeters / 100),Height is CalcM .

convertToCm(CalcFeetInches,Height):- CalcCm is (CalcFeetInches * 2.540), convertToM(CalcCm,Height).

calculate_height(Feet,Inches,Height):- 
 CalcFeet is (Feet * 12 ), CalcFeetInches is 
 (CalcFeet + Inches), convertToCm(CalcFeetInches,Height).
 
 % calculates the body mass index of the individual, R is Height squared.
 calculate_bmi(Height,Weight,Bmi):-nl, R is (Height * Height) , Bmi is (Weight / R).
 
 %calculate kilograms weight to pounds
 calculate_weight(Weight,Kilogram):- Kilogram is (Weight * 0.453592).
 
 
 
 % bmi classification based on height(meters) and weight(pounds).
classify_bmi(Bmi):- nl , write(Bmi),(Bmi >= 30.0 -> Status = 'Obese'; Bmi < 18.5 -> 
Status = 'UnderWeight'; Bmi >= 18.5 ,
 Bmi =< 24.9 -> Status = 'NormalWeight';
 Bmi >= 25 , Bmi =< 29.9 -> Status = 'OverWeight'
),nl,write(Status).


input(Feet,Inches,Weight):-
calculate_height(Feet,Inches,Height), % returned Height in meters.
calculate_weight(Weight,Kilogram),  % returned weight in pounds.
calculate_bmi(Height,Kilogram,Bmi), % returns individual calculated body mass index.
classify_bmi(Bmi). 

