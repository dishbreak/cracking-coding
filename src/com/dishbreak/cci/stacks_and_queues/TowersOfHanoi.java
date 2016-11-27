package com.dishbreak.cci.stacks_and_queues;


public class TowersOfHanoi {
	private static class HanoiRod implements Stack<Integer> {
		
		private StackNode head = null;
		
		private String name;
		
		public HanoiRod(String name) {
			this.name = name;
		}

		@Override
		public Integer pop() {
			Integer result = null;
			
			if (head != null) {
				result = head.data;
				head = head.next;
			}
			
			return result;
		}

		@Override
		public Integer peek() {
			Integer result = null;
			
			if (head != null) {
				result = head.data;
			}
			
			return result;
		}

		@Override
		public void push(Integer data) {
			StackNode node = new StackNode(data);
			
			if (head != null && data.intValue() > head.data.intValue()) {
				System.err.println("ERROR on rod " + name  + ": can't move! Disk " + data + " is bigger than Disk" + head.data );
			}
			
			node.next = head;
			head = node;
		}

		@Override
		public boolean isEmpty() {
			return head != null;
		}
		
		public String toString() {
			return name;
		}
		
		
	}
	
	private static class StackNode {
		public Integer data;
		public StackNode next;
		
		public StackNode(Integer data) {
			this.data = data;
		}
	}
	
	private static HanoiRod startRod;
	private static HanoiRod auxRod;
	private static HanoiRod endRod;
	
	public static void play(int disks) {
		startRod = new HanoiRod("Start");
		auxRod = new HanoiRod("Aux");
		endRod = new HanoiRod("End");
		
		for (Integer i = disks; i > 0; i--) {
			startRod.push(i);
		}
		
		move(5, startRod, endRod, auxRod);
	}
	
	private static void move(int diskNum, HanoiRod source, HanoiRod dest, HanoiRod aux) {
		if (diskNum > 0) {
			move(diskNum-1, source, aux, dest);
			System.out.println("Moving " +  diskNum + ": " + source + " -> " + dest);
			dest.push(source.pop());
			move(diskNum-1, aux, dest, source);
		}
	}
	
	public static void main(String[] args) {
		play(5);
	}
	
	
}
