#! /usr/bin/env python
import os, sys

if __name__ == '__main__':
	if len(sys.argv) < 2:
		print 'usage: %s <path>' % sys.argv[0]
		sys.exit(1)
print os.path.realpath(sys.argv[1])
