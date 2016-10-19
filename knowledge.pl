% knowledge base for moh

% BMI WEIGHT CATEGORIES

underweight([18.5]). % below 
normalweight([18.5,24.9]). % over and less than 24.9
overweight([25,29.9]) . % 25 - 29.9	Overweight
obese(30.0). % over 30


% BMI Calculations
printHeight(Height):- write("Height in meters =  "), nl,(format('~3f',[Height])),classify_bmi(Height).
convertToM(CalcMeters):- CalcM is (CalcMeters / 100),printHeight(CalcM).
convertToCm(CalcFeetInches):- CalcCm is (CalcFeetInches * 2.540),convertToM(CalcCm).

calculate_height(Feet,Inches):- 
 CalcFeet is (Feet * 12 ), CalcFeetInches is 
 (CalcFeet + Inches), convertToCm(CalcFeetInches).
 
classify_bmi(Bmi):-  (Bmi >= 30.0 -> Status = 'Obese'; Bmi < 18.5 -> 
Status = 'UnderWeight'; Bmi >= 18.5 ,
 Bmi =< 24.9 -> Status = 'NormalWeight';
 Bmi >= 25 , Bmi =< 29.9 -> Status = 'OverWeight'
),nl,write(Status).