import csv

def knapsack(capacity, weights, values):
    n = len(values)
    # Create a DP table with (n+1) rows and (capacity+1) columns
    dp = [[0] * (capacity + 1) for _ in range(n + 1)]
    
    for i in range(n + 1):
        for w in range(capacity + 1):
            if i == 0 or w == 0:
                dp[i][w] = 0
            elif weights[i-1] <= w:
                dp[i][w] = max(values[i-1] + dp[i-1][w - weights[i-1]], dp[i-1][w])
            else:
                dp[i][w] = dp[i-1][w]
    return dp[n][capacity]

def read_knapsack_items(filename):
    values = []
    weights = []
    
    with open(filename, 'r') as file:
        reader = csv.DictReader(file)
        for row in reader:
            values.append(int(row['value']))
            weights.append(int(row['weight']))
    return values, weights

if __name__ == "__main__":
    # Read items from CSV
    values, weights = read_knapsack_items('knapsack.csv')
    
    # Get knapsack capacity from user
    capacity = int(input("Enter knapsack capacity: "))
    
    # Calculate maximum value
    max_value = knapsack(capacity, weights, values)
    
    # Display result
    print(f"\nItems loaded from CSV: {len(values)}")
    print(f"Knapsack capacity: {capacity}")
    print(f"Maximum achievable value: {max_value}")