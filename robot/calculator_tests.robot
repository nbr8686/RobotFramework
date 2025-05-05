*** Settings ***
Library    com.trysol.apps.calculator.CalculatorKeywords

*** Test Cases ***
Verify Addition
    Open Calculator
    ${result}=    Calculate    5 + 3
    Should Be Equal    ${result}    8
    Close Calculator