package com.dishbreak.testlab.hashtable;

public class HashMap {

	private static final Integer NUM_BUCKETS = 256;
	private static final Integer BUCKET_SIZE = 4;
	
	private Entry[][] bucketArray;
	private final Integer numBuckets;
	private final Integer numBucketSize;
	
	
	public HashMap(Integer numBuckets, Integer numBucketSize) {
		this.numBuckets = numBuckets;
		this.numBucketSize = numBucketSize;
		bucketArray = new Entry[numBuckets][numBucketSize];
	}

	public HashMap() {
		this(NUM_BUCKETS, BUCKET_SIZE);
	}
	
	public void put(String key, Integer value) {
		Integer hash = key.hashCode();
		Integer index = Math.abs(hash % numBuckets);
		Entry keyPair = new Entry(key, value);
		
		boolean inserted = false;
		for (int i=0; i < numBucketSize; i++) {
			if (bucketArray[index][i] == null) {
				bucketArray[index][i] = keyPair;
				inserted = true;
				break;
			}
		}
		
		if (!inserted) {
			System.err.println("OOPS! The bucket is full, too many collisions");
		}

	}

	public Integer get(String key) {
		Integer hash = key.hashCode();
		Integer index = Math.abs(hash % numBuckets);
		Integer value = null;
		
		for (int i=0; i < numBucketSize; i++) {
			if (bucketArray[index][i] != null) {
				if (key.equals(bucketArray[index][i].key)) {
					value = bucketArray[index][i].value;
				}
			}
		}
		
		return value;
		
	}

	public boolean containsKey(String key) {
		Integer hash = key.hashCode();
		Integer index = Math.abs(hash % numBuckets);
		boolean found = false;
		
		for (int i=0; i < numBucketSize; i++) {
			if (bucketArray[index][i] != null) {
				if (key.equals(bucketArray[index][i].key)) {
					found = true;
				}
			}
		}
		
		return found;
	}

}
