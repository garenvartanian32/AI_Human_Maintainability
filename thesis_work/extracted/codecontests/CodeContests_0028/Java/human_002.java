var rebus = readline().split(' ').map(String);

var count = {plus: 1, minus: 0}; // number of + and - in rebus

for (var i = 0; i < rebus.length; i++) {
	if (rebus[i] == '+') {
		count.plus += 1;
	} else if (rebus[i] == '-') {
		count.minus += 1;
	}
	if (rebus[i] == '=') {
		n = Number(rebus[i + 1]);
	}
}

// Do I need to modify the -? or the +?s
var diff = 0;
var a = 0; // a --> change a number of ?'s to n
var b = 1; // b --> change another -? to b
var counter = 0; // used to count how many ?'s are replaced with n (count a)

// Modify 'minus' ?
if (count.plus - count.minus > n) {
	// modify 'minus'
	// minus_sum is what the sum of all -? needs to be
	var minus_sum = count.plus - n;
	// Checking to see if this sum falls between the number of -? and the number of -? * n
	if (minus_sum >= count.minus && minus_sum <= count.minus * n) {

		diff = minus_sum - count.minus;
		a = Math.floor(diff / (n - 1)); 
		b = diff % (n - 1) + 1; 

		rebus[0] = 1;

		counter = 0;
		for (var i = 1; i < rebus.length; i++)  {
			// I only want to replace -? with -n a times, the next replacement should be -b, the rest should be 1
			if (counter == a) {
				if (rebus[i] == '-') {
					rebus[i + 1] = b;
					break;
				}
			} else {
				if (rebus[i] == '-') {
					rebus[i + 1] = n;
					counter += 1;
				}
			}
		}

		for (var i = 1; i < rebus.length; i++) {
			if (rebus[i] == '?') {
				rebus[i] = 1;
			}
		}

		rebus = rebus.join(' ');

		print('Possible');
		print(rebus);

	} else {
		print('Impossible');
	}

} else {
	//modify 'plus'
	// plus_sum is what the sum of all +? needs to be
	var plus_sum = count.minus + n;
	// Checking to see if this sum falls between the number of -? and the number of -? * n
	if (plus_sum >= count.plus && plus_sum <= count.plus * n) {

		diff = plus_sum - count.plus;
		a = Math.floor(diff / (n - 1)); 
		b = diff % (n - 1) + 1;

		if (diff === 0) {
			rebus[0] = 1;
		} else if (a === 0) {
			rebus[0] = b;
		} else if (a >= 1) {
			rebus[0] = n;
			counter = 1;
			for (var i = 1; i < rebus.length; i++)  {
				// I only want to replace +? with +n a times, the next replacement should be +b, the rest should be 1
				if (counter == a) {
					if (rebus[i] == '+') {
						rebus[i + 1] = b;
						break;
					}
				} else {
					if (rebus[i] == '+') {
						rebus[i + 1] = n;
						counter += 1;
					}
				}
			}
		}

		for (var i = 1; i < rebus.length; i++) {
			if (rebus[i] == '?') {
				rebus[i] = 1;
			}
		}

		rebus = rebus.join(' ');

		print('Possible');
		print(rebus);

	} else {
		print('Impossible');
	}
}