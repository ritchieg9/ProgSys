                    ORG               $27AA    
HOLA         EQU               $FF2
                    BLS                10235
                    LDY                 #%110011
                    LBLT               4062
                   TBEQ              D,10099
                   BVS                  $2752
WOW         INC                  SCS
                   ORG                 $2DA
                   BLE                  832
SCS           LSL                   10
                   IBNE                 SP,643
                  LBHI                 $19F
                  END
