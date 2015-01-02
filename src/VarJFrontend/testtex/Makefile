# type "make" command in Unix to create $MAINFILE.pdf file
MAINFILE=main

all:
	pdflatex $(MAINFILE).tex

clean:
	rm -rf *.ps *.log *.dvi *.aux *.*% *.lof *.lop *.lot *.toc *.idx *.ilg *.ind *.bbl *.blg $(MAINFILE).out

distclean: clean
	rm $(MAINFILE).pdf
