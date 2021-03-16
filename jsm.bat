@echo off
    set MAIN_ENTRY = %CD%\build\MainKt
    if "%~1" == "knit-pdf" goto KnitToPdf
    if "%~1" == "test" goto CoreTester
    if "%~1" == "generate-template" goto GenerateTemplate
    if "%~1" == "edit-template" goto EditTemplateOnline
    if "%~1" == "javac" goto CompileJavaClasses
    if "%~1" == "help" goto ShowDocs
    if "%~1" == "" goto ShowHelp

    echo "Invalid action argument (%~1)! Options: [knit-pdf | test | generate-template | edit-tempalte | javac | help]"
    goto AfterHelp

    :KnitToPdf
        if %2. ==. (
            echo Missing markdown file input
            goto ShowHelp
        )
        echo Generating Pdf....
        kotlinc %CD%\Main.kt -d %CD%\build && kotlin %MAIN_ENTRY% knit-pdf %2
        goto AfterHelp

    :CoreTester
        echo Testing JSM Core....
        if not exist tests_log mkdir tests_log
        kotlinc %CD%\Main.kt -d %CD%\build && kotlin %MAIN_ENTRY% test
        goto AfterHelp

    :GenerateTemplate
        echo Generating markdown template....
        if not exist templates mkdir templates
        kotlinc %CD%\Main.kt -d %CD%\build && kotlin %MAIN_ENTRY% generate-template %2
        goto AfterHelp

    :EditTemplateOnline
         if %2. ==. (
            echo Missing markdown file input
            goto ShowHelp
        )
        :: open website for editing markdown + live preview of non executable parts
        start "" https://stackedit.io/app#
        goto AfterHelp

    :CompileJavaClasses
        echo Deleting data from build...
        del /S build
        echo Compiling all .java files
        for %%f in (core/*.java*) do (
            javac -d build core/%%f
            echo Compiling %%f
        ) 
        echo Succesfully compiled....
        goto AfterHelp

    :ShowDocs
        echo List of valid commands:
        echo "   jsm knit-pdf @fileName => generate pdf from a template inside the /templates directory"
        echo "   jsm test => run the application test for the core methods"
        echo "   jsm generate-template [@fileName] => generates a basic markdown template @fileName param is optional"
        echo "   jsm edit-template @fileName => edit a specific template from the /templates directory"
        echo "   jsm javac - compile all the java classes inside of core"
        goto AfterHelp 

    :ShowHelp
        echo Run "jsm help" to view the list of valid commands.
    :AfterHelp
PAUSE