//package com.company.pagerank;

import java.util.*;

public class PageRank {

  /** Create 2D array 'path' for storing edges and array 'pagerank' for storing ranks  */
  public int path[][] = new int[10][10];
  public double pagerank[] = new double[10];

  /** Core method with perform interative calculation of page ranks */
  public void calc(double totalNodes) {
    
    /** Define variables and Damping Factor */
    double InitialPageRank;
    double OutgoingLinks=0;
    double DampingFactor = 0.85;
    double TempPageRank[] = new double[10];
    int ExternalNodeNumber;
    int InternalNodeNumber;
    int k=1;
    int ITERATION_STEP=1;

    /** Set initial ranks to 1/N */
    InitialPageRank = 1/totalNodes;
    System.out.printf(" Total Number of nodes: "+totalNodes+"\t Initial PageRank of All Nodes: "+InitialPageRank+"\n");
    for(k=1;k<=totalNodes;k++)
      {this.pagerank[k] = InitialPageRank;}

    /** Print initial page ranks */
    System.out.printf("\n Initial PageRank Values, 0th Step \n");
    for(k=1;k<=totalNodes;k++)
      {System.out.printf("PageRank of "+k+" is :\t"+this.pagerank[k]+"\n");}
    
    /** Define number of iterations */
    while(ITERATION_STEP<=5) {

      /** Store PageRank for all nodes in a temporary array */
      for(k=1;k<=totalNodes;k++) {
          TempPageRank[k]=this.pagerank[k];
          this.pagerank[k]=0;
      }
      /** Perform a single iteration; calculate updated page ranks*/
      for(InternalNodeNumber=1;InternalNodeNumber<=totalNodes;InternalNodeNumber++) {
        for(ExternalNodeNumber=1;ExternalNodeNumber<=totalNodes;ExternalNodeNumber++) {
          if(this.path[ExternalNodeNumber][InternalNodeNumber] == 1) {
            k=1;
            OutgoingLinks=0; //Count the number of outgoing links for each ExternalNodeNumber
            while(k<=totalNodes) {
              if(this.path[ExternalNodeNumber][k] == 1) {
                OutgoingLinks=OutgoingLinks+1; //Counter for outgoing links
              }
            k=k+1;
            }
          this.pagerank[InternalNodeNumber]+=TempPageRank[ExternalNodeNumber]*(1/OutgoingLinks);
          }
        }
      }

      /** Incorporate damping factor */
      for(k=1;k<=totalNodes;k++) {
        this.pagerank[k]=(1-DampingFactor)+DampingFactor*this.pagerank[k];
      }
      
      /** Display interim page ranks */
      System.out.printf("\n\tAfter Step "+ITERATION_STEP+"\n");
      for(k=1;k<=totalNodes;k++) {
        System.out.printf("Page Rank of "+k+" is :\t"+this.pagerank[k]+"\n");
      }

      /** Increment interation step */
      ITERATION_STEP = ITERATION_STEP+1;
    }
    /** Display final page ranks */
    System.out.printf("\n\tFinal Page rank : \n");
    for(k=1;k<=totalNodes;k++) {
      System.out.printf("Page Rank of "+k+" is :\t"+this.pagerank[k]+"\n");
    }
  }

  public static void main(String args[]) {
    /** Main function: takes parameters from user, runs PageRank algorithm, results are printed
     * input: number of nodes (user input)
     * output: none
     */
    int nodes,i,j;
    Scanner in = new Scanner(System.in);
    System.out.println("Enter the Number of WebPages \n");
    nodes = in.nextInt();
    PageRank p = new PageRank();
    System.out.println("Enter the Adjaceny matrix with 1 -> Path & 0 -> No Path Between two pages: \n");
    for(i=1;i<=nodes;i++)
      for(j=1;j<=nodes;j++) {
        p.path[i][j]=in.nextInt();
        if(j==1)
          p.path[i][j]=0;
      }
    p.calc(nodes); 
    }
  }

