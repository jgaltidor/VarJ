#! /usr/bin/env python
import os, sys, glob
from os.path import join, isdir, basename
from optparse import OptionParser

if __name__=='__main__':
	if len(sys.argv) < 3:
		print 'usage: %s <patterns separated by file seps> <dir paths separated by file seps>' \
			% basename(sys.argv[0])
		sys.exit(1)

	patterns = sys.argv[1].split(os.pathsep)
	dirs = sys.argv[2].split(os.pathsep)
	if not patterns:
		print 'No patterns specified'
		sys.exit(1)
	if not dirs:
		print 'No directories specified'
		sys.exit(1)
	for dirpath in dirs:
		if not os.path.isdir(dirpath):
			print '%s is not an existing directory' % dirpath
			sys.exit(1)
	matchingFiles = []
	for dirpath in dirs:
		for root, dirs, files in os.walk(dirpath):
			for pat in patterns:
				dirpattern = join(root, pat)
				matchingFileInDir = glob.glob(dirpattern)
				matchingFiles.extend(matchingFileInDir)
	for f in matchingFiles: print f,
