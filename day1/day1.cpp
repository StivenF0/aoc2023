#include <fstream>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

bool isNumeric(char entry) {
  return isdigit(entry);
}

int main() {
  // Getting all the lines on the file
  string line;
  ifstream input("input.txt");
  vector<string> lines;
  while (getline(input, line)) {
    lines.push_back(line);
  }
  input.close();

  // Getting the numbers
  vector<string> numbers;
  for (string line : lines) {
    char numbersOnLine[] = {'0', '0'};
    int idx1 = 0;
    bool foundSecond = false;
    
    for (char c : line) {
      if (!isNumeric(c)) {
        idx1++;
        continue;
      }
      numbersOnLine[0] = c;
      break;
    }

    int lineSize = line.size();
    for (int i = line.size() - 1; i > 0; i--) {
      if (!isNumeric(line[i]) || i == idx1) {
        continue;
      }

      numbersOnLine[1] = line[i];
      foundSecond = true;
      break;
    }

    string number;
    number += numbersOnLine[0];
    number += foundSecond ? numbersOnLine[1] : numbersOnLine[0];
    
    numbers.push_back(number);
  }

  // Doing the sum
  int sum = 0;
  for (string num : numbers) {
    sum += stoi(num);
  }

  cout << "Sum: " << sum << endl;

  return 0;
}
