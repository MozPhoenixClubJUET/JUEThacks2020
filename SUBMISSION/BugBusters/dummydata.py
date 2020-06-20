import numpy as np
import time

#                 97-103, 0-1, 0-130, 0-1, 0-2, 0-1
headers = 'fever,BodyPain,age,RunnyNose,diffBreath,infectionProb'
entrycount = 800
lower_limit_temp = 97.0
upper_limit_temp = 103.1
upper_age = 105

with open('Data.csv', 'w+') as file:
	file.write(headers + '\n')
	np.random.seed(int(time.time()))
	temp_s = np.random.uniform(low=lower_limit_temp, high=upper_limit_temp, size=(entrycount,)).round(1)
	bodypain_s = np.random.randint(2, size=entrycount)
	age_s = np.random.randint(upper_age, size=entrycount)
	runnynose_s = np.random.randint(2, size=entrycount)
	diffbre_s = np.random.randint(3, size=entrycount)
	for i in range(entrycount):
		temp_c = temp_s[i]
		bodypain_c = bodypain_s[i]
		age_c = age_s[i]
		runnynose_c = runnynose_s[i]
		diffbre_c = diffbre_s[i]
		
		inf_c = 0
		
		hightemp_c = int(temp_c > 98)
		# bodypain_c
		aged_c = int(age_c > 40)
		# runnynose_c
		# diffbre_c
		
		inf_c = int(
			(3 * hightemp_c
			+ 4 * bodypain_c
			+ 3 * aged_c
			+ 4 * runnynose_c
			+ 3 * diffbre_c)
			/ 20
			> 0.6
		)
		
		file.write(','.join(map(str, [
						temp_c,
						bodypain_c,
						age_c,
						runnynose_c,
						diffbre_c,
						inf_c
		]))+'\n')