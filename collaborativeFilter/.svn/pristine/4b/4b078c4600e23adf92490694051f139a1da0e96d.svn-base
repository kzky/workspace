'''
Created on 2012/11/08

@author: kzk
'''

from collections import defaultdict;
import time;

def calcSlowCf(item2UsersMap):
    start = time.time();
    print "start: ", start
    
    item2ReItemsMap = defaultdict(list);
    items = item2UsersMap.keys();
    count = 0;
    for itemI in items:
        count += 1;
        usersI = item2UsersMap[itemI];
        for itemJ in items:
            if itemI != itemJ:
                jcoeff = len(usersI.intersection(item2UsersMap[itemJ]));
                       
                if jcoeff != 0:
                    jcoeff = float(jcoeff)/(len(item2UsersMap[itemI]) + len(item2UsersMap[itemJ]) - jcoeff);
                    print count, jcoeff;
                #item2ReItemsMap[itemI].append((itemJ, jcoeff))

    end = time.time();
    print "end : ", end
    
    return (item2ReItemsMap, end - start);

def calcFastCf(item2UsersMap, user2ItemsMap):
    start = time.time();
    print "start: ", start
    
    item2ReItemsMap = defaultdict(list);
    items = item2UsersMap.keys();
    norm_items = defaultdict(int);

    for item in items:
        norm_items[item] = len(item2UsersMap[item])
    
    count = 0;    
    for itemI in items:
        
        print count, ": " , itemI;
        count += 1; 
        jcoeffs = defaultdict(int); ## all jackard coefficients for itemI 
        for userI in item2UsersMap[itemI]:
            for itemJ in user2ItemsMap[userI]:
                jcoeffs[itemJ] += 1;

        
        norm_itemI = norm_items[itemI]
        for item in jcoeffs.keys():
            jcoeffs[item] = jcoeffs[item]/(norm_itemI + norm_items[item] - jcoeffs[item]) ## all jackard coefficients for itemI  

    end = time.time();
    print "end : ", end
    
    return (item2ReItemsMap, end - start);

##--- main ---## 
fp_in = open("/home/kzk/dataset/drbd/73789/rireki.tsv", "r");
item2UsersMap = defaultdict(set); 
user2ItemsMap = defaultdict(set); 
for line in fp_in:
    dat = line[:-1].split("\t");
    if dat[6] != "0":
        item2UsersMap[dat[1]].add(dat[2]);
        user2ItemsMap[dat[2]].add(dat[1]);

#resultSlow = calcSlowCf(item2UsersMap);
#print resultSlow[1];
    
resultFast = calcFastCf(item2UsersMap, user2ItemsMap);
print resultFast[1];


