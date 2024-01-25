# [Baekjoon] 2042 구간 합 구하기
## 설명

어떤 **N개의 수**가 주어져 있다. 그런데 중간에 수의 변경이 빈번히 일어나고 그 중간에 **어떤 부분의 합을 구하려 한다**. 만약에 **1,2,3,4,5 라는 수가 있고, 3번째 수를 6으로 바꾸고 2번째부터 5번째까지 합을 구하라고 한다면 17**을 출력하면 되는 것이다. 그리고 그 상태에서 다섯 번째 수를 2로 바꾸고 3번째부터 5번째까지 합을 구하라고 한다면 12가 될 것이다.

## 입력

첫째 줄에 수의 개수 **N**(1 ≤ N ≤ 1,000,000)과 M(1 ≤ M ≤ 10,000), K(1 ≤ K ≤ 10,000) 가 주어진다. **M은 수의 변경이 일어나는 횟수이고, K는 구간의 합을 구하는 횟수**이다. 그리고 둘째 줄부터 N+1번째 줄까지 N개의 수가 주어진다. 그리고 N+2번째 줄부터 N+M+K+1번째 줄까지 세 개의 정수 a, b, c가 주어지는데, a가 1인 경우 b(1 ≤ b ≤ N)번째 수를 c로 바꾸고 a가 2인 경우에는 b(1 ≤ b ≤ N)번째 수부터 c(b ≤ c ≤ N)번째 수까지의 합을 구하여 출력하면 된다.

입력으로 주어지는 모든 수는 -2^63보다 크거나 같고, 2^63-1보다 작거나 같은 정수이다.

## 출력

첫째 줄부터 K줄에 걸쳐 구한 구간의 합을 출력한다. 단, 정답은 -2^63보다 크거나 같고, 2^63-1보다 작거나 같은 정수이다.

### 예시 입력

### 예시 출력

```jsx
5 2 2
1
2
3
4
5
1 3 6
2 2 5
1 5 2
2 3 5
```

```jsx
17
12
```

## 풀이 과정

시간 복잡도를 생각하지 않고 풀이를 진행해봅시다. 

N개의 수가 주어져 있고 우리는 어떤 구간의 합을 구하면 되는 간단한 문제입니다. 

예를 들어, 문제에 나와있듯이 1, 2, 3, 4, 5라는 숫자가 존재하고 여기서 2~5 사이의 값을 더한다고 가정해봅시다. 

```java
int [] arr = new int [] {1,2,3,4,5};
int sum = 0;
for(int i = 1; i < 5 ; i++) {
	sum += arr[i];
}
```

N개의 수를 K번 탐색해야 하니 시간 복잡도는 O(NK)가 될 것입니다. (하지만 이는 1,000,000 * 10,000 하게 되어 시간 초과가 발생합니다) 

우리는 세그먼트 트리를 활용해야 합니다. 

### **세그먼트 트리란?**

> 이진 트리의 형태 중 하나이며 빠르게 특정 구간의 합을 구할 수 있다는 특징이 있다.
> 

![Untitled](%5BBaekjoon%5D%202042%20%E1%84%80%E1%85%AE%E1%84%80%E1%85%A1%E1%86%AB%20%E1%84%92%E1%85%A1%E1%86%B8%20%E1%84%80%E1%85%AE%E1%84%92%E1%85%A1%E1%84%80%E1%85%B5%20a5f82f91b68d4c4ea3e05e64ac410690/Untitled.png)

위 그림은 [2, 6, 4, -3, 5, -1, 6, 10] 에 대한 세그먼트 트리입니다. 

리프 노트는 각각의 원소를 의미하고 리프 노드가 아닌 노드는 구간의 합을 의미합니다. 

이를 통해 어떻게 합을 빠르게 구할 수 있을까요?

예를 들어, 인덱스 2에서 5사이의 값을 찾고 싶다고 가정해봅시다. 

우리는 [0-3] + [4-5] 를 통해 빠르게 그 값을 도출해 낼 수 있습니다. 

(이전에 언급했듯 N번 반복해서 구할 수 있지만 그 값이 커진다면 시간 초과가 날 것 입니다)

**세그먼트 트리는 어떻게 구현할까요?**

```java
public class SegmentTree {

		// 배열로 구현 
    private long[] tree;

    public SegmentTree(int n) {
				// 높이는 log2(n) 이 때 무조건 올림을 해주어야 합니다.  
        double height = Math.ceil(Math.log(n) / Math.log(2)) + 1;
				// 2 ^ (height)
        long count = Math.round(Math.pow(2, height));
        tree = new long[count];
    }

    long init(long[] arr, int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        } else {
						// 재귀로 진행합니다.
						// 자식 노드는 현재 노드의 2x, 2x+1 입니다.
            return tree[node] = init(arr, node * 2, start, (start + end) / 2)
	+ init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
        }
    }

    long sum(int node, int start, int end, int left, int right) {
				// 범위를 벗어났다면 0을 반환합니다.
        if (end < left || right < start) {
            return 0;
				// 범위 사이에 있다면 우선 해당 노드의 값은 반환합니다.
        } else if (left <= start && end <= right) {
            return tree[node];
				// 범위 외에 있는 값들을 재귀로 찾습니다. 
        } else {
            return sum(node * 2, start, (start + end) / 2, left, right)
	+ sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        }
    }

    long update(int node, int start, int end, int index, long changeValue) {
        if (index < start || end < index) {
            return tree[node];
				// 리프 노드에 도착한다면 값을 바꿔줍니다.
        } else if (start == index && end == index) {
            return tree[node] = changeValue;
        } else {
				// 바꾼 값으로 구간 합을 대체합니다.
            return tree[node] =
	update(node * 2, start, (start + end) / 2, index, changeValue) +
	    update(node * 2 + 1, (start + end) / 2 + 1, end, index, changeValue);
        }
    }
}
```