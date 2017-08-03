echo ***********install***********
call mvn clean deploy -Denv=release -Dmaven.test.skip


goto end


if errorlevel 1 goto error

:error
echo Error Happen
:end
echo *****************************************
echo *               OK!		     *
echo ***************************************** 
pause



