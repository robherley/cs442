# hw2
make sure to instal `pygments` from pip2 for syntax highlighting


latex toolchain
```
"latex-workshop.latex.toolchain": [
		{
			"command": "latexmk",
			"args": [
				"-latexoption=-shell-escape",
				"-synctex=1",
				"-interaction=nonstopmode",
				"-file-line-error",
				"-pdf",
				"%DOC%"
			]
		}
	]
```