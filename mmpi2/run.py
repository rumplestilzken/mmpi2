#!/usr/bin/env python3

from PyQt5.QtWidgets import *

import sys
from mmpi2.gui.MainWindow import MainWindow

from mmpi2.setup import *

def main():
    setup.process()

    app = QApplication(sys.argv)
    window = MainWindow()

    sys.exit(app.exec())
    return


if __name__ == '__main__':
    main()