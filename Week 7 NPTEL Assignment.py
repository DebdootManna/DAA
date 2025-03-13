'''
We are well into the 21st century and school children are taught dynamic programming in class 4. The IOI training camp has degenerated into an endless sequence of tests, with negative marking. At the end of the camp, each student is evaluated based on the sum of the best contiguous segment (i.e., no gaps) of marks in the overall sequence of tests.

Students, however, have not changed much over the years and they have asked for some relaxation in the evaluation procedure. As a concession, the camp coordinators have agreed that students are allowed to drop upto a certain number of tests when calculating their best segment.

For instance, suppose that Lavanya is a student at the training camp and there have been ten tests, in which her marks are as follows.

Test	  1  	   2  	  3  	   4  	  5  	   6  	   7  	   8  	   9  	  10  
Marks	  6  	  -5  	  3  	  -7  	  6  	  -1  	  10  	  -8  	  -8  	  8  
In this case, without being allowed to drop any tests, the best segment is tests 5–7, which yields a total of 15 marks. If Lavanya is allowed to drop upto 2 tests in a segment, the best segment is tests 1–7, which yields a total of 24 marks after dropping tests 2 and 4. If she is allowed to drop upto 6 tests in a segment, the best total is obtained by taking the entire list and dropping the 5 negative entries to get a total of 33.

You will be given a sequence of N test marks and a number K. You have to compute the sum of the best segment in the sequence when upto K marks may be dropped from the segment.

Solution hint
For 1 ≤ i ≤ N, 1 ≤ j ≤ K, let Best[i][j] denote the maximum segeent ending at position i with at most j marks dropped. Best[i][0] is the classical maximum subsegment or maximum subarray problem. For j ≥ 1; inductively compute Best[i][j] from Best[i][j-1].

Input format
The first line of input contains two integers N and K, where N is the number of tests for which marks will be provided and K is the limit of how many entries may be dropped from a segment.

This is followed by N lines of input each containing a single integer. The marks for test i, i ∈ {1,2,…,N} are provided in line i+1.

Output format
The output is a single number, the maximum marks that can be obtained from a segment in which upto K values are dropped.

Constraints
You may assume that 1 ≤ N ≤ 104 and 0 ≤ K ≤ 102. The marks for each test lie in the range [-104 … 104]. In 40% of the cases you may assume N ≤ 250.

Example:
We now illustrate the input and output formats using the example described above.
Sample input:
10 2
6
-5
3
-7
6
-1
10
-8
-8
8
Sample output:
24
Evaluation
For each function, there are some public test cases and some (hidden) private test cases.
"Compile and run" will evaluate your submission against the public test cases.
"Submit" will evaluate your submission against the hidden private test cases and report a score on 100.
The private test cases include some large inputs that validate the efficiency of your algorithm.'
'''

def max_segment_with_drops(marks, n, k):
    """
    Find the maximum segment sum after dropping up to k elements
    
    Args:
        marks: List of test marks
        n: Number of tests
        k: Maximum number of elements that can be dropped
        
    Returns:
        Maximum possible sum of a segment after dropping up to k elements
    """
    # Create array to store the maximum sum ending at current position with j drops
    max_ending_here = [0] * (k + 1)
    
    # Global maximum sum
    max_so_far = float('-inf')
    
    for mark in marks:
        # Update max_ending_here for each number of drops j
        # Process in reverse to avoid using the updated values
        for j in range(k, 0, -1):
            # Two options: drop current mark or keep it
            max_ending_here[j] = max(max_ending_here[j-1], max_ending_here[j] + mark)
        
        # For zero drops, standard Kadane's algorithm
        max_ending_here[0] = max(0, max_ending_here[0] + mark)
        
        # Update global maximum
        max_so_far = max(max_so_far, max(max_ending_here))
    
    return max_so_far

# Read input
if __name__ == '__main__':
    n, k = map(int, input().split())
    marks = []
    for _ in range(n):
        marks.append(int(input()))
    
    result = max_segment_with_drops(marks, n, k)
    print(result)