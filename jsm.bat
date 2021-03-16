@echo off
    if %1% == knit-pdf goto KnitToPdf
    if %1% == test goto CoreTester
    if %1% == generate-template goto GenerateTemplate
    if %1% == edit-template goto EditTemplateOnline
    if %1% == javac goto CompileJavaClasses
    if %1% == help goto ShowDocs
    goto End

    :KnitToPdf
        if %2. ==. (
            echo Missing markdown file input
            goto End
        )
        echo Generating Pdf....
        kotlinc Main.kt -d build && cd build && kotlin MainKt knit-pdf %2 && cd..
        goto End

    :CoreTester
        echo Testing JSM Core....
        if not exist tests_log mkdir tests_log
        kotlinc Main.kt -d build && cd build && kotlin MainKt test && cd..
        goto End

    :GenerateTemplate
        echo Generating markdown template....
        if not exist templates mkdir templates
        kotlinc Main.kt -d build && cd build && kotlin MainKt generate-template %2 && cd..
        goto End

    :EditTemplateOnline
         if %2. ==. (
            echo Missing markdown file input
            goto End
        )
        :: open website for editing markdown + live preview of non executable parts
        start "" https://stackedit.io/app#
        goto End

    :CompileJavaClasses
        echo Deleting data from build...
        del /S build
        echo Compiling all .java files
        for %%f in (core/*.java*) do (
            javac -d build core/%%f
            echo Compiling %%f
        ) 
        echo Succesfully compiled....
        goto End

    :ShowDocs
        echo List of valid commands:
        echo "   jsm knit-pdf @fileName => generate pdf from a template inside the /templates directory"
        echo "   jsm test => run the application test for the core methods"
        echo "   jsm generate-template [@fileName] => generates a basic markdown template @fileName param is optional"
        echo "   jsm edit-template @fileName => edit a specific template from the /templates directory"
        echo "   jsm javac - compile all the java classes inside of core"
        goto AfterHelp 

    :End
        echo Run "jsm help" to view the list of valid commands.
    :AfterHelp
PAUSE